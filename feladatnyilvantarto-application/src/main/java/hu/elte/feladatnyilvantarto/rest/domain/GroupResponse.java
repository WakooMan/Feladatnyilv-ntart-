package hu.elte.feladatnyilvantarto.rest.domain;

public class GroupResponse {
    private String leader;
    private Iterable<UserResponse> users;

    public GroupResponse(){}

    public GroupResponse(String leader, Iterable<UserResponse> users) {
        this.leader = leader;
        this.users = users;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Iterable<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(Iterable<UserResponse> users) {
        this.users = users;
    }
}
