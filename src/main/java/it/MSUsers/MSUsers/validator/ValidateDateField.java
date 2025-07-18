package it.MSUsers.MSUsers.validator;

import it.MSUsers.MSUsers.exception.DataValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeParseException;

public class ValidateDateField implements ConstraintValidator<ValidateDateRange, String>  {

    private int minYearNum;
    private int maxYearNum;

    @Override
    public void initialize(ValidateDateRange constraintAnnotation) {
        // Get the parameters from the annotation
        this.minYearNum = constraintAnnotation.min_year_num();
        this.maxYearNum = constraintAnnotation.max_year_num();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // If the date field is optional and null is allowed, return true.
        // If null is not allowed, use @NotNull in addition to this custom validator.
        if (value == null) {
            return true; // Or false, depending on whether null dates are considered valid
        }

        // Get the current year
        int currentYear = Year.now().getValue();

        // Calculate the allowed min and max years based on the current year and annotation parameters
        int minAllowedYear = currentYear - minYearNum;
        int maxAllowedYear = currentYear - maxYearNum;

        // Get the year of the date being validated
        // convert string to local date
        LocalDate valueConverted;
        boolean conversionStatus = false;
        try {
            valueConverted = LocalDate.parse(value);
            conversionStatus = true;
            int yearOfValue = valueConverted.getYear();
            // Perform the validation check
            boolean isValid = (yearOfValue <= minAllowedYear && yearOfValue >= maxAllowedYear);

            // If validation fails, you can customize the error message further
            if (!isValid) {
                context.disableDefaultConstraintViolation(); // Disable default message
                context.buildConstraintViolationWithTemplate(
                        "Date year must be between " + minAllowedYear + " and " + maxAllowedYear + " (inclusive)."
                ).addConstraintViolation();
            }

            return isValid;
        } catch (Exception e) {
            if (!conversionStatus) {
                context.disableDefaultConstraintViolation(); // Disable default message
                context.buildConstraintViolationWithTemplate(
                        "Invalid Date format we accept only the following format yyyy-MM-dd"
                ).addConstraintViolation();
            }
            return conversionStatus;
        }


    }
}
