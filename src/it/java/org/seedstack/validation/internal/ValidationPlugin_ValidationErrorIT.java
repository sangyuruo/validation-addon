/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal;

import org.seedstack.validation.ValidationException;
import org.seedstack.validation.internal.pojo.DummyServiceFieldValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.seed.it.SeedITRunner;
import org.seedstack.seed.it.Expect;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SeedITRunner.class)
@Expect(value = ValidationException.class, step = Expect.TestingStep.INSTANTIATION)
public class ValidationPlugin_ValidationErrorIT {

    @Inject
    DummyServiceFieldValidation serviceField;

    @Test
    public void trigger() {
        assertThat(serviceField).isNull();
    }
}
