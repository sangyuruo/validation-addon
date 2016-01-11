/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.it.SeedITRunner;
import org.seedstack.validation.ValidationException;
import org.seedstack.validation.ValidationService;
import org.seedstack.validation.internal.pojo.Bean;
import org.seedstack.validation.internal.pojo.MyImpl;
import org.seedstack.validation.internal.pojo.Pojo;
import org.seedstack.validation.internal.pojo.PojoWithDeepValidation;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
public class ValidationServiceIT {

    @Inject
    ValidationService validationService;

    @Test
    public void validationServiceIsInjected() {
        assertThat(validationService).isNotNull();
    }

    @Test
    public void throwsExceptionOnInvalidPojo() {
        try {
            validationService.staticallyHandle(new Pojo(Pojo.State.INVALID));
            Assertions.failBecauseExceptionWasNotThrown(ValidationException.class);
        } catch (ValidationException validationException) {
            expectViolations(validationException, 3);
        }
    }

    private void expectViolations(ValidationException validationException, int expected) {
        Set<ConstraintViolation<?>> constraintViolations = validationException
                .get(ValidationService.JAVAX_VALIDATION_CONSTRAINT_VIOLATIONS);
        assertThat(constraintViolations).hasSize(expected);
    }

    @Test
    public void doNothingOnValidPojo() {
        validationService.staticallyHandle(new Pojo(Pojo.State.VALID));
    }

    @Test
    public void throwsExceptionOnDeepValidation() {
        try {
            validationService.staticallyHandle(new PojoWithDeepValidation());
            Assertions.failBecauseExceptionWasNotThrown(ValidationException.class);
        } catch (ValidationException validationException) {
            expectViolations(validationException, 4);
        }
    }

    @Test
    public void validationShouldWorkOnInterface() {
        try {
            validationService.staticallyHandle(new MyImpl());
            Assertions.failBecauseExceptionWasNotThrown(ValidationException.class);
        } catch (ValidationException validationException) {
            expectViolations(validationException, 1);
        }
    }

    @Test
    public void validationShouldWorkOnGetter() {
        try {
            Bean candidate = new Bean();
            candidate.setHour(25);
            validationService.staticallyHandle(candidate);
            Assertions.failBecauseExceptionWasNotThrown(ValidationException.class);
        } catch (ValidationException validationException) {
            expectViolations(validationException, 1);
        }
    }
}
