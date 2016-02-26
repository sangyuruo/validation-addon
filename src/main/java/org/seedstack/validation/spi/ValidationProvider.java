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
