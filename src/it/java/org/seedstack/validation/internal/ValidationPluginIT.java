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
import org.seedstack.validation.internal.pojo.*;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

@RunWith(SeedITRunner.class)
public class ValidationPluginIT {

    @Inject
    ParamValidation serviceParam;

    @Inject
    FieldValidationOK serviceField;

    @Inject
    ParamReturnType serviceReturnType;

    @Inject
    WithoutValidation serviceWithoutValidation;

    @Test
    public void services_are_well_injected() {
        Assertions.assertThat(serviceParam).isNotNull();
        Assertions.assertThat(serviceField).isNotNull();
        Assertions.assertThat(serviceReturnType).isNotNull();
        Assertions.assertThat(serviceWithoutValidation).isNotNull();
    }

    @Test
    public void param_not_null_validations_ok() {
        serviceParam.validateNotNullParam("");
    }

    @Test(expected = ConstraintViolationException.class)
    public void param_not_null_validations_are_well_intercepted() {
        serviceParam.validateNotNullParam(null);
    }

    @Test
    public void param_valid_validations_ok() {
        serviceParam.validateValidParam(new Pojo(Pojo.State.VALID));
    }


    @Test(expected = ConstraintViolationException.class)
    public void param_valid_validations_are_well_intercepted() {
        serviceParam.validateValidParam(new Pojo(Pojo.State.INVALID));
    }

    @Test
    public void not_null_return_validations_ok() {
        serviceReturnType.validateNotNullReturn("");
    }

    @Test(expected = ConstraintViolationException.class)
    public void not_null_return_validations_are_well_intercepted() {
        serviceReturnType.validateNotNullReturn(null);
    }

    @Test
    public void valid_return_validations_ok() {
        serviceReturnType.validateValidReturn(Pojo.State.VALID);
    }

    @Test(expected = ConstraintViolationException.class)
    public void valid_return_validations_are_well_intercepted() {
        serviceReturnType.validateValidReturn(Pojo.State.INVALID);
    }

}
