/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal;

import io.nuun.kernel.core.AbstractPlugin;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 * This plugin handle validation via jsr303 and jsr349 via hibernate-validation.
 *
 * @author epo.jemba@ext.mpsa.com
 */
public class ValidationPlugin extends AbstractPlugin {
    private ValidationService validationService = new ValidationServiceInternal();
    private ValidationModule validationModule;
    private ValidatorFactory validatorFactory;

    @Override
    public String name() {
        return "validation";
    }

    @Override
    public Object nativeUnitModule() {
        if (validationModule == null) {
            validatorFactory = Validation.buildDefaultValidatorFactory();
            validationModule = new ValidationModule(validatorFactory, validationService);
        }
        return validationModule;
    }

    @Override
    public void stop() {
        if (validatorFactory != null) {
            validatorFactory.close();
        }
    }
}
