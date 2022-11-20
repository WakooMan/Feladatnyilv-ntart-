package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Credentials {

    @Id
    @GeneratedValue
    private int id;
    private String loginName;
    private String password;

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
