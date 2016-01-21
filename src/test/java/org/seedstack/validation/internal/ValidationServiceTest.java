/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal;


import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.seedstack.validation.internal.fixtures.*;

public class ValidationServiceTest {

    private ValidationService underTest;

    @Before
    public void init() {
        underTest = new ValidationServiceInternal();
    }

    @Test
    public void isCandidateForStaticValidation() {
        Assertions.assertThat(underTest.candidateForStaticValidation(BeanField.class)).isTrue();
        Assertions.assertThat(underTest.candidateForStaticValidation(BeanAll.class)).isTrue();
    }

    @Test
    public void isNotCandidateForStaticValidation() {
        Assertions.assertThat(underTest.candidateForStaticValidation(BeanNominal.class)).isFalse();
    }

    @Test
    public void isCandidateForDynamicValidation() {
        Assertions.assertThat(underTest.candidateForDynamicValidation(BeanMethodParam.class)).isTrue();
        Assertions.assertThat(underTest.candidateForDynamicValidation(BeanMethodReturnType.class)).isTrue();
        Assertions.assertThat(underTest.candidateForDynamicValidation(BeanAll.class)).isTrue();
    }

    @Test
    public void isNotCandidateForDynamicValidation() {
        Assertions.assertThat(underTest.candidateForDynamicValidation(BeanNominal.class)).isFalse();
    }

}
