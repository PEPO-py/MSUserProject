package it.MSUsers.MSUsers.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ValidateDateField.class) // Specifies the validator class
@Target({ FIELD, PARAMETER }) // Where the annotation can be applied
@Retention(RUNTIME)
public @interface ValidateDateRange {
    // Default error message if validation fails
    String message() default "";

    // take the range from the annotation above the field min and max in string format that then be modified in date
    int min_year_num() default 18;
    int max_year_num() default 100;

    // Allows for grouping constraints
    Class<?>[] groups() default {};

    // Can be used by clients to carry metadata about the constraint
    Class<? extends Payload>[] payload() default {};
}

