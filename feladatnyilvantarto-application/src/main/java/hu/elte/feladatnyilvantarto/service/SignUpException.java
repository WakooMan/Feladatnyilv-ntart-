package hu.elte.feladatnyilvantarto.service;

public class SignUpException extends RuntimeException
{
    public SignUpException() {
        super("Username already exists.");
    }
}
