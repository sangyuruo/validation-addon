/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation;


import org.seedstack.seed.ErrorCode;
import org.seedstack.seed.SeedException;

/**
 * Exception class for validation errors.
 *
 * @author epo.jemba@ext.mpsa.com
 * @deprecated This exception is no longer used and will be removed in the next version.
 * Use the standard {@link javax.validation.ConstraintViolationException} instead.
 */
@Deprecated
public class ValidationException extends SeedException {

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
