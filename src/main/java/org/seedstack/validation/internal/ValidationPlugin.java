/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal;

import io.nuun.kernel.api.plugin.InitState;
import io.nuun.kernel.api.plugin.context.InitContext;
import io.nuun.kernel.core.AbstractPlugin;
import org.seedstack.seed.core.utils.SeedReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 * This plugin handles validation through jsr303 and jsr349.
 */
public class ValidationPlugin extends AbstractPlugin {
    private static Logger logger = LoggerFactory.getLogger(ValidationPlugin.class);
    private ValidatorFactory validatorFactory = null;

    @Override
    public String name() {
        return "validation";
    }

    @Override
    public InitState init(InitContext initContext) {
        if (SeedReflectionUtils.isClassPresent("javax.validation.Validation")) {
            validatorFactory = Validation.buildDefaultValidatorFactory();
        } else {
            logger.info("Validation API is not available, validation is disabled");
        }

        return InitState.INITIALIZED;
    }

    @Override
    public Object nativeUnitModule() {
        if (validatorFactory != null) {
            return new ValidationModule(validatorFactory, new ValidationServiceInternal());
        } else {
            return null;
        }
    }

    @Override
    public void stop() {
        if (validatorFactory != null) {
            validatorFactory.close();
        }
    }

    /**
     * Retrieves the {@link ValidatorFactory} initialized by the validation plugin.
     *
     * @return An instance of {@link ValidatorFactory} if validation is enabled, null otherwise.
     */
    public ValidatorFactory getValidatorFactory() {
        return validatorFactory;
    }
}
