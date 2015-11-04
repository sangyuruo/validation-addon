---
title: "Dynamic validation"
addon: "Validation"
repo: "https://github.com/seedstack/validation-addon"
menu:
    AddonValidation:
        weight: 30
---

Bean Validation 1.1 introduces Dynamic Validation, also called "Design by Contract". You can now let Seed validate
inputs and outputs of your services by simply providing constraint annotations on parameters and/or on methods'
return types. For example, consider the code below:

```java
public void sendEmail(@Email String email, @Valid Message message) {
    // ...
}
```

`@Email` validation guarantees by design that the email parameter will be valid when used in `sendEmail` method.
In the same way, `@Valid` guarantees that the message will be valid.

# Validation on method execution
## On method return

```java
import javax.validation.constraints.NotNull;

public class MyService {

    @NotNull
    public Object doSomething(Object param) {
        Object toReturn = null;
        // ...
        return toReturn;
    }
}
```

## On method parameters

```java
import javax.validation.constraints.NotNull;

public class MyService2 {

    public void doSomething(@NotNull Object param) {
        // do something
    }
}
```

## When using your services

```java
@Inject
MyService myService;

@Inject
MyService2 myService2;
```

Seed provides a proxy which handles input parameters and return value verification throwing a
{{< java "org.seedstack.validation.ValidationException" >}} upon any constraint violation.
