/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal;

import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.Provides;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;
import org.seedstack.seed.core.internal.CorePlugin;
import org.seedstack.validation.spi.ValidationConcern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;

@ValidationConcern
class ValidationModule extends AbstractModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationModule.class);

    private final ValidatorFactory validatorFactory;
    private final ValidationService validationService;

    ValidationModule(ValidatorFactory validatorFactory, ValidationService validationService) {
        this.validatorFactory = validatorFactory;
        this.validationService = validationService;
    }

    @Override
    protected void configure() {
        bind(ValidatorFactory.class).toInstance(validatorFactory);
        enableValidationOnInjectionPoints();
        if (isDynamicValidationSupported()) {
            configureDynamicValidation();
            requestInjection(validationService);
            bind(ValidationService.class).toInstance(validationService);
        }
    }

    private void enableValidationOnInjectionPoints() {
        bindListener(staticValidationMatcher(validationService), new StaticValidationProvisionListener(validationService));
    }

    private void configureDynamicValidation() {
        bindInterceptor(Matchers.any(), dynamicValidationMatcher(validationService), new MethodValidationInterceptor(validationService));
    }

    private boolean isDynamicValidationSupported() {
        ExecutableValidator executableValidator = null;
        try {
            executableValidator = validatorFactory.getValidator().forExecutables();
        } catch (Throwable t) {
            LOGGER.info("Unable to create the dynamic validator, support for dynamic validation disabled");
            LOGGER.debug(CorePlugin.DETAILS_MESSAGE, t);
        }
        return executableValidator != null;
    }

    @Provides
    Validator provideValidator() {
        return validatorFactory.getValidator();
    }

    private Matcher<? super Binding<?>> staticValidationMatcher(final ValidationService validationService) {
        return new AbstractMatcher<Binding<?>>() {
            @Override
            public boolean matches(Binding<?> t) {
                return validationService.candidateForStaticValidation(t.getKey().getTypeLiteral().getRawType());
            }
        };
    }

    private Matcher<Method> dynamicValidationMatcher(final ValidationService validationService) {
        return new AbstractMatcher<Method>() {
            @Override
            public boolean matches(Method t) {
                return validationService.candidateForDynamicValidation(t);
            }
        };
    }

}
