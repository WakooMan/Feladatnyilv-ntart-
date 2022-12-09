package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Credentials {
    private String loginName;
    private String password;

    public Credentials(String login, String pwd){
        this.loginName=login;
        this.password =pwd;
    }

    public Credentials() {

    }

    public void setPassword(String pw)
    {
        password = pw;
    }
    public String getPassword()
    {
        return password;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }
}

