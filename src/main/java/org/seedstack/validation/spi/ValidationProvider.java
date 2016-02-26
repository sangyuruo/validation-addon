/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.spi;

import io.nuun.kernel.api.annotations.Facet;

import javax.validation.ValidatorFactory;

/**
 * Provides access to bean validation.
 */
@Facet
public interface ValidationProvider {
    /**
     * Retrieves the initialized {@link ValidatorFactory}.
     *
     * @return An instance of {@link ValidatorFactory} if validation is enabled, null otherwise.
     */
    ValidatorFactory getValidatorFactory();
}
