package hu.elte.feladatnyilvantarto.webdomain;

import javax.validation.constraints.*;

public class UserForm
{
    @NotEmpty(message= "Please give me your username!")
    private String username;
    @NotEmpty(message = "Please give me your password!")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserForm()
    {
        username="";
        password="";
    }
}
