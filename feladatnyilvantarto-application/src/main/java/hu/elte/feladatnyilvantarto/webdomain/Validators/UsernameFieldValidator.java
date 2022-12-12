package hu.elte.feladatnyilvantarto.webdomain.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameFieldValidator implements ConstraintValidator<IsUsername,String> {
    private String messageNotEmpty;
    private String messageNotRightSize;

    @Override
    public void initialize(IsUsername constraintAnnotation) {
        messageNotEmpty = constraintAnnotation.messageNotEmpty();
        messageNotRightSize = constraintAnnotation.messageNotRightSize();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if(value.isEmpty())
        {
            context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
            return false;
        }
        if(value.length() < 2 || value.length() > 20)
        {
            context.buildConstraintViolationWithTemplate(messageNotRightSize).addConstraintViolation();
            return false;
        }
        return true;
    }
}
