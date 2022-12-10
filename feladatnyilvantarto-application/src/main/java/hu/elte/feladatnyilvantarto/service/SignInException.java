package hu.elte.feladatnyilvantarto.service;

public class SignInException extends RuntimeException
{
    public SignInException()
    {
        super("Username or password is invalid");
    }
}
