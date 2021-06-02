package com.kruger.hero.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * PositiveIntValidator.
 * 
 * @author Kruger on 02/06/2021
 * @version 1.0
 * @since 1.0.0
 */
public class PositiveIntValidator implements ConstraintValidator<PositiveIntConstraint, Integer> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(PositiveIntConstraint value) {
        /**
         * PositiveConstraint.
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Integer contactField, ConstraintValidatorContext cxt) {
        if (null == contactField) {
            return false;
        }
        return contactField > 0;
    }

}
