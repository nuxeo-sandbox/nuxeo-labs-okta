package nuxeo.labs.okta.core.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.*;
import org.nuxeo.ecm.platform.auth.saml.SAMLCredential;
import org.nuxeo.ecm.platform.auth.saml.user.AbstractUserResolver;
import org.nuxeo.ecm.platform.usermanager.UserManager;
import org.nuxeo.runtime.api.Framework;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.xml.XMLObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jfletcher on 1/12/17.
 */
public class OktaUserResolver extends AbstractUserResolver {

    private static final Log log = LogFactory.getLog(OktaUserResolver.class);

    @Override
    public String findNuxeoUser(SAMLCredential credential) {
        try {
            UserManager userManager = Framework.getLocalService(UserManager.class);
            Map<String, Serializable> query = new HashMap<>();
            query.put(userManager.getUserEmailField(), credential.getNameID().getValue());
            DocumentModelList users = userManager.searchUsers(query, null);
            if (users.isEmpty()) {
                return null;
            }
            // Note: assumes the first user is the one we want.
            return (String) users.get(0).getPropertyValue(userManager.getUserIdField());

        } catch (NuxeoException e) {
            log.error(
                    "Error while search user in UserManager using email " +
                            credential.getNameID().getValue(), e);
            return null;
        }
    }

    @Override
    public String getLoginName(SAMLCredential samlCredential) {
        return null;
    }

    @Override
    public DocumentModel updateUserInfo(DocumentModel user, SAMLCredential credential) {
        try {
            UserManager userManager = Framework.getLocalService(UserManager.class);
            NuxeoPrincipal principal =
                    userManager.getPrincipal((String) user.getPropertyValue(userManager.getUserIdField()));
            principal.setEmail(credential.getNameID().getValue());
            for (Attribute attribute : credential.getAttributes()) {
                switch (attribute.getName()) {
                    case "firstName":
                        principal.setFirstName(attribute.getAttributeValues().get(0).getDOM().getTextContent());
                        break;
                    case "lastName":
                        principal.setLastName(attribute.getAttributeValues().get(0).getDOM().getTextContent());
                        break;
                    case "groups":
                        addToGroups(attribute, principal);
                        break;
                    default:
                        break;
                }
            }
            userManager.updateUser(principal.getModel());
            return userManager.getUserModel(principal.getName());

        } catch (NuxeoException e) {
            log.error(
                    "Error while search user in UserManager using email " +
                            credential.getNameID().getValue(), e);
            return null;
        }
    }

    protected void addToGroups(Attribute attribute, NuxeoPrincipal principal) {
        List<String> groups = principal.getGroups();
        for (XMLObject value : attribute.getAttributeValues()) {
            String group = value.getDOM().getTextContent();
            if ("Everyone".equals(group)) {
                // do nothing
            } else {
                groups.add(group);
            }
        }
        principal.setGroups(groups);
    }
}
