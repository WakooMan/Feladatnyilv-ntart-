package hu.elte.feladatnyilvantarto.webdomain.Validators;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@Constraint(validatedBy = PasswordFieldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsPassword {
    String message() default "Wrong Password!";
    String messageNotEmpty() default "Please give me your password!";
    String messageNotPassword() default "Password should contain at least 8 characters, one upper case character, one lower case character and one number!";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
