package it.MSUsers.MSUsers.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleCodeValidator implements ConstraintValidator<ValidateRoleCode, Integer> {
    private int[] acceptedValues;

    @Override
    public void initialize(ValidateRoleCode constraintAnnotation) {
        // Get the parameters from the annotation
        this.acceptedValues = constraintAnnotation.acceptedValue();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // If the date field is optional and null is allowed, return true.
        // If null is not allowed, use @NotNull in addition to this custom validator.
        if (value == null) {
            return true; // Or false, depending on whether null dates are considered valid
        }

        // Perform the validation check
        boolean isValid = true;
        int howManyTrue = 0;
        for (int i=0; i < this.acceptedValues.length; i++) {
            if(value == this.acceptedValues[i]) howManyTrue++;
        }
        if(howManyTrue == 0) isValid = false;

        return isValid;
    }
}
