package nuxeo.labs.okta.core.authentication;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.auth.saml.SAMLCredential;
import org.nuxeo.ecm.platform.auth.saml.user.AbstractUserResolver;

/**
 * Created by jfletcher on 1/12/17.
 */
public class OktaUserResolver extends AbstractUserResolver {
    @Override
    public String findNuxeoUser(SAMLCredential samlCredential) {
        return null;
    }

    @Override
    public String getLoginName(SAMLCredential samlCredential) {
        return null;
    }

    @Override
    public DocumentModel updateUserInfo(DocumentModel documentModel, SAMLCredential samlCredential) {
        return null;
    }
}
