package hu.elte.feladatnyilvantarto.service.exceptions;

public class SignUpException extends RuntimeException
{
    public SignUpException() {
        super("Username already exists.");
    }
}
