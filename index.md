---
title: "Basics"
name: "Validation"
repo: "https://github.com/seedstack/validation-addon"
author: "SeedStack"
description: "Provides bean validation 1.1 (JSR 303 & 349)."
min-version: "15.11+"
backend: true
weight: -1
tags:
    - "bean"
    - "validation"
zones:
    - Addons
menu:
    AddonValidation:
        weight: 10
---

SeedStack validation add-on allows developers to use Bean Validation 1.0 and 1.1:

* Bean Validation 1.0 brings static validation (aka JSR 303).
* Bean Validation API 1.1 brings dynamic validation (aka JSR 349).

Data validation is a very common concern at each and every layer of an application. As such, it has been standardized
through Bean Validation using JSR 303 and JSR 349. Implementation uses **Hibernate Validator** that you need to add
explicitly in your project:

* Use Hibernate Validator version 4.x to support Bean Validation 1.0 only (like in strict JEE6 environments)
* Use Hibernate Validator version 5.x to support Bean Validation 1.1.

To use this add-on add the following dependency:

{{< dependency g="org.seedstack.addons.validation" a="validation" >}}

