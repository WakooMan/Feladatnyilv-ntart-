package hu.elte.feladatnyilvantarto.webdomain.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordFieldValidator implements ConstraintValidator<IsPassword,String> {

    private String messageNotEmpty;
    private  String messageNotPassword;

    @Override
    public void initialize(IsPassword isPassword)
    {
        messageNotEmpty = isPassword.messageNotEmpty();
        messageNotPassword = isPassword.messageNotPassword();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if(value.isEmpty())
        {
            context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
            return false;
        }
        if(value.length() < 8 || value.chars().filter(c -> Character.isDigit(c)).count() == 0 || value.chars().filter(c -> Character.isUpperCase(c)).count() == 0 || value.chars().filter(c -> Character.isLowerCase(c)).count() == 0)
        {
            context.buildConstraintViolationWithTemplate(messageNotPassword).addConstraintViolation();
            return false;
        }
        return true;
    }
}
