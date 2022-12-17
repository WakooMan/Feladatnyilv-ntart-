package hu.elte.feladatnyilvantarto.webdomain.other;

public class GroupUser {
    private int id;
    private String name;
    public GroupUser(){}
    public GroupUser(int id,String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
