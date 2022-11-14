package Domain;

public class Credentials {
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
