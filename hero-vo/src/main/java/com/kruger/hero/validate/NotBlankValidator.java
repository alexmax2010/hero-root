package com.kruger.hero.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * NotBlankValidator.
 * 
 * @author acachiguango on 02/06/2021
 * @version 1.0
 * @since 1.0.0
 */
public class NotBlankValidator implements ConstraintValidator<NotBlankConstraint, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(NotBlankConstraint value) {
        /**
         * NotBlankValidator.
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        return StringUtils.isNotBlank(contactField);
    }
}
