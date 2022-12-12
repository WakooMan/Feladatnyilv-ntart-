package hu.elte.feladatnyilvantarto.webdomain.Validators;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsernameFieldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUsername {
    String message() default "Wrong username";
    String messageNotEmpty() default "Please give me your username!";
    String messageNotRightSize() default "Username should be between 2 and 20 characters long!";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
