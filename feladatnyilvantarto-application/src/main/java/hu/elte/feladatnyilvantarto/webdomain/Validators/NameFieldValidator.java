package hu.elte.feladatnyilvantarto.webdomain.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class NameFieldValidator implements ConstraintValidator<IsName,String> {
    private String messageNotEmpty;
    private String messageNotRightSize;
    private String messageNotRightFormat;

    @Override
    public void initialize(IsName constraintAnnotation) {
        messageNotEmpty = constraintAnnotation.messageNotEmpty();
        messageNotRightFormat = constraintAnnotation.messageNotRightFormat();
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
        if(value.length() < 4 || value.length() > 50)
        {
            context.buildConstraintViolationWithTemplate(messageNotRightSize).addConstraintViolation();
            return false;
        }
        if(value.split(" ").length == 0 || Arrays.stream(value.split(" ")).anyMatch(str -> !Character.isUpperCase(str.charAt(0)) || str.substring(1).chars().anyMatch(c -> !Character.isLowerCase(c))))
        {
            context.buildConstraintViolationWithTemplate(messageNotRightFormat).addConstraintViolation();
            return false;
        }
        return true;
    }
}
