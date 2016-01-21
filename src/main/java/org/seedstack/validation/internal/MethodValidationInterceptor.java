/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

class MethodValidationInterceptor implements MethodInterceptor {
    private final ValidationService validationService;

    MethodValidationInterceptor(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return this.validationService.dynamicallyHandleAndProceed(invocation);
    }

}
