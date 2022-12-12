package hu.elte.feladatnyilvantarto.webdomain;

import hu.elte.feladatnyilvantarto.webdomain.Validators.IsName;
import hu.elte.feladatnyilvantarto.webdomain.Validators.IsPassword;
import hu.elte.feladatnyilvantarto.webdomain.Validators.IsUsername;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpForm {
    @IsUsername
    private String username;

    @IsPassword
    private String password;
    @IsName
    private String name;

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

    public SignUpForm()
    {
        username="";
        password="";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
