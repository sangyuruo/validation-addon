/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal.fixtures;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BeanMethodParam {
    private int age;

    private Long longNumber;

    private String name;

    private String firstName;


    public int getAge() {
        return age;
    }

    public void setAge(@Range(min = 0, max = 200) int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Nom obligatoire") @Size(min = 1, max = 10) String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(groups = {Group1Checks.class}) String firstName) {
        this.firstName = firstName;
    }

    public Long getLongNumber() {
        return longNumber;
    }

    public void setLongNumber(
            @NotNull @Min(value = 10L) @Max(value = 999999999999999999L)
            Long longNumber) {
        this.longNumber = longNumber;
    }


}
