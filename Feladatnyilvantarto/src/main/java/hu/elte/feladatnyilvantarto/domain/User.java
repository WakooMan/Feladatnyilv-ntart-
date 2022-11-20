package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@Table(name = "USERS")
public class User
{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Embedded
    private Credentials credentials;
    @OneToMany
    private List<Group> groups;
    @OneToMany(mappedBy = "leader", cascade = {CascadeType.MERGE})
    private List<Group> groupsLed;
    private UserType userType;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE})
    private List<TimeMeasure> userTimeMeasures;

    public User(String name, Credentials credentials,String userType)
    {
        this.name = name;
        this.credentials = credentials;
        this.userType = UserType.valueOf(userType);
        this.groups = new ArrayList<Group>();
    }

    public User() {

    }

    public List<Group> getGroupsLed() {
        return groupsLed;
    }

    public void setGroupsLed(List<Group> groupsLed) {
        this.groupsLed = groupsLed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public void setUserType(String userType) {
        this.userType = UserType.valueOf(userType);
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public Credentials getCredentials()
    {
        return credentials;
    }
    public void setCredentials(Credentials cred)
    {
        this.credentials = cred;
    }
    public List<Group> getGroups()
    {
        return groups;
    }

    public List<Ticket> getAssignedTickets()
    {
        List<Ticket> result = new ArrayList<Ticket>();
        for(Group group:groups)
        {
            result.addAll(group.getTickets().stream().filter((t) -> (userType == UserType.Worker)?t.getAssignees().contains(this):t.getAssigner().equals(this)).collect(Collectors.toList()));
        }
        return result;
    }

    public List<TimeMeasure> getUserTimeMeasures() {
        return userTimeMeasures;
    }

    public void setUserTimeMeasures(List<TimeMeasure> userTimeMeasures) {
        this.userTimeMeasures = userTimeMeasures;
    }

}