/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal;

import com.google.inject.spi.ProvisionListener;

/**
 * Validates provisioned instances eligible for static validation.
 */
class StaticValidationProvisionListener implements ProvisionListener {

    private ValidationService validationService;

    public StaticValidationProvisionListener(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public <A> void onProvision(ProvisionInvocation<A> provision) {
        A injectee = provision.provision();
        validationService.staticallyHandle(injectee);
    }
}
