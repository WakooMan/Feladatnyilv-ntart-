package hu.elte.feladatnyilvantarto.webdomain.Validators;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameFieldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsName {
    String message() default "Wrong name";
    String messageNotEmpty() default "Please give me your name!";
    String messageNotRightSize() default "Name should be between 4 and 50 characters long!";
    String messageNotRightFormat() default "Name should contain names separated by a space!";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
