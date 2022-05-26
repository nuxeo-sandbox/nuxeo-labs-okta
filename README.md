# About

This Nuxeo Package contains a custom resolver for Okta to map Okta user profile to Nuxeo profile.

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

# License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

# About Nuxeo

Nuxeo Platform is an open source Content Services platform, written in Java. Data can be stored in both SQL & NoSQL databases.

The development of the Nuxeo Platform is mostly done by Nuxeo employees with an open development model.

The source code, documentation, roadmap, issue tracker, testing, benchmarks are all public.

Typically, Nuxeo users build different types of information management solutions for [document management](https://www.nuxeo.com/solutions/document-management/), [case management](https://www.nuxeo.com/solutions/case-management/), and [digital asset management](https://www.nuxeo.com/solutions/dam-digital-asset-management/), use cases. It uses schema-flexible metadata & content models that allows content to be repurposed to fulfill future use cases.

More information is available at [www.nuxeo.com](https://www.nuxeo.com).
