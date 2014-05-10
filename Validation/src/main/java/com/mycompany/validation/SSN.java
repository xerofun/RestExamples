package com.mycompany.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Brian
 */

@Size(min = 11, max = 11)
@Pattern(regexp = "^(\\d{3}-?\\d{2}-?\\d{4})$")
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = {})
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface SSN
{
    // TODO: Pull out into ValidationMessages.properties
    String message() default "Invalid SSN";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
