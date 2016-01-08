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
import org.seedstack.validation.ValidationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

public class ValidationExceptionMapperTest {

    @Test
    public void testIsProvider() {
        ValidationExceptionMapper.class.isAnnotationPresent(Provider.class);
    }

    @Test
    public void testReturnError400() {
        Response response = new ValidationExceptionMapper()
                .toResponse(new ValidationException(ValidationErrorCode.VALIDATION_ISSUE));
        Assertions.assertThat(response.getStatus()).isEqualTo(400);
    }
}
