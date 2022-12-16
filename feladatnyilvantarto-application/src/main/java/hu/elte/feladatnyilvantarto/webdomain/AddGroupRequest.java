package hu.elte.feladatnyilvantarto.webdomain;

import hu.elte.feladatnyilvantarto.domain.User;

public class AddGroupRequest {
    private String name;
    private User leader;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }
}
