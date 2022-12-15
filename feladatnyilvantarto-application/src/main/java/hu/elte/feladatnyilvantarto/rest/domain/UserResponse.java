package hu.elte.feladatnyilvantarto.rest.domain;

public class UserResponse {
    private String user;
    private int count;

    public  UserResponse(){}

    public UserResponse(String user, int count) {
        this.user = user;
        this.count = count;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
