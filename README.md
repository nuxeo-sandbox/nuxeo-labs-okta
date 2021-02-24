# About

This Nuxeo Package contains a custom resolver for Okta to map Okta user profile to Nuxeo profile.

[![Build Status](https://qa.nuxeo.org/jenkins/buildStatus/icon?job=Sandbox/sandbox_nuxeo-labs-okta-master)](https://qa.nuxeo.org/jenkins/job/Sandbox/job/sandbox_nuxeo-labs-okta-master/)

# Requirements

Build requires the following software:
- git
- maven

# Limitations

The mapping is "fixed"; it assumes the only fields that will be used from Okta are:

* email (also used for the user ID)
* firstName
* lastName
* groups **

** The `groups` field should be defined as a custom profile property on the Okta side; this is not a reference to Okta's groups feature. Define an array field and place the Nuxeo group names here.

# Build

```
git clone https://github.com/nuxeo-sandbox/nuxeo-labs-okta
cd nuxeo-labs-okta
mvn clean install
# or
mvn clean install -DskipTests
```

# Deploy

Install the marketplace package on your Nuxeo instance.

Add a `userResolverClass` parameter to your authenticators contribution in Nuxeo Studio:

```
<parameter name="userResolverClass">nuxeo.labs.okta.core.authentication.OktaUserResolver</parameter>
```

A full Studio contribution example is provided in the [studio](studio) folder. There is an icon included as well, add this to **Resources -> Images** in Studio Modeler.

If you need help setting up Okta, please see [this documentation](okta-setup/README.md).

# Support

**These features are not part of the Nuxeo Production platform.**

These solutions are provided for inspiration and we encourage customers to use them as code samples and learning resources.

This is a moving project (no API maintenance, no deprecation process, etc.) If any of these solutions are found to be useful for the Nuxeo Platform in general, they will be integrated directly into platform, not maintained here.

# Licensing

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)

# About Nuxeo

[Nuxeo](www.nuxeo.com), developer of the leading Content Services Platform, is reinventing enterprise content management (ECM) and digital asset management (DAM). Nuxeo is fundamentally changing how people work with data and content to realize new value from digital information. Its cloud-native platform has been deployed by large enterprises, mid-sized businesses and government agencies worldwide. Customers like Verizon, Electronic Arts, ABN Amro, and the Department of Defense have used Nuxeo's technology to transform the way they do business. Founded in 2008, the company is based in New York with offices across the United States, Europe, and Asia.

Learn more at www.nuxeo.com.