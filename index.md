---
title: "Overview"
addon: "Validation"
repo: "https://github.com/seedstack/validation-addon"
author: "SeedStack"
description: "Provides bean validation 1.1 (JSR 303 & 349)."
min-version: "15.7+"
menu:
    AddonValidation:
        weight: 10
---

SeedStack validation add-on allows developers to use Bean Validation 1.0 and 1.1.

- Bean Validation 1.0 brings static validation aka JSR 303.
- Bean Validation API 1.1 brings dynamic validation aka JSR 349. 

Data validation is a very common concern at each and every layer of an application. As such, it has been standardized 
through Bean Validation using JSR 303 and JSR 349. Implementation uses **Hibernate Validator** that you need to add explicitly in 
your project:

- Use Hibernate Validator version 4.x to support Bean Validation 1.0 only (like in strict JEE6 environments)
- Use Hibernate Validator version 5.x to support Bean Validation 1.1

# Maven dependency

To install Seed validation add-on just add the following dependency snippet:

    <dependency>
        <groupId>org.seedstack.addons</groupId>
        <artifactId>validation</artifactId>
    </dependency>

Please note that validation only works on instances managed by Seed (i.e. injectable instances).

# Additional resources

* Specifications :
  * [http://beanvalidation.org/1.0/spec/](http://beanvalidation.org/1.0/spec/ "Bean Validation 1.0 - JSR 303")
  * [http://beanvalidation.org/latest-draft/spec/](http://beanvalidation.org/latest-draft/spec/ "Bean Validation 1.1 Final - JSR 349")

* Bean Validation 1.0:
  * [http://blog.octo.com/jsr-303-bean-validation-etat-des-lieux/](http://blog.octo.com/jsr-303-bean-validation-etat-des-lieux/)
  * [http://www.jmdoudoux.fr/java/dej/chap-validation_donnees.htm](http://www.jmdoudoux.fr/java/dej/chap-validation_donnees.htm)

* Bean Validation 1.1 :
  * [http://blog.frankel.ch/design-by-contract-and-bean-validation](http://blog.frankel.ch/design-by-contract-and-bean-validation)
