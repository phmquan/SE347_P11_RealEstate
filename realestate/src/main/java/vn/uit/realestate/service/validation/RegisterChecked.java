package vn.uit.realestate.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RegisterValidator.class)
@Target({ElementType.TYPE}) // Adjusted to apply to the class level
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterChecked {

  String message() default "User register validation failed";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
