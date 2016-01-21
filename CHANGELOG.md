# Version 2.2.0 (2016-01-28)

* [new] An `ExceptionMapper` for `ConstraintViolationException` is provided by default.
* [new] The `ValidatorFactory` is now injectable.
* [chg] The `ValidationService` is deprecated and replaced by `Validator` from the spec (injected or obtained through `ValidatorFactory`).
* [brk] The `org.seedstack.validation.ValidationException` is replaced by the standard `ConstraintViolationException`.

# Version 2.1.0 (2015-11-15)

* [chg] Refactored as an add-on and updated to work with Seed 2.1.0+

# Version 2.0.0 (2015-07-30)

* [new] Initial Open-Source release.
