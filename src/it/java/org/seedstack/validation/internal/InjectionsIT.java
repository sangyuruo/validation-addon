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

import javax.inject.Inject;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RunWith(SeedITRunner.class)
public class InjectionsIT {

    @Inject
    private ValidatorFactory validatorFactory;

    @Inject
    private Validator validator;

    @Test
    public void isValidatorFactoryInjectable() {
        Assertions.assertThat(validatorFactory).isNotNull();
    }

    @Test
    public void isValidatorInjectable() {
        Assertions.assertThat(validator).isNotNull();
    }
}
