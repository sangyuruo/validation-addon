/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal.pojo;

import org.seedstack.seed.it.ITBind;

import javax.validation.constraints.NotNull;


@ITBind
public class FieldValidationOK {

    @NotNull
    private Object param = new Object();

    public void doSomethingAwesome(Object param) {
        this.param = param;
    }
}
