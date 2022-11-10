package Domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class User
{
    private int id;
    private Credentials credentials;
    private ArrayList<Group> groups;
    private UserType userType;

    public User(int id,Credentials credentials,UserType userType)
    {
        this.id = id;
        this.credentials = credentials;
        this.userType = userType;
        this.groups = new ArrayList<Group>();
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
    public ArrayList<Group> getGroups()
    {
        return groups;
    }

    public ArrayList<Ticket> getAssignedTickets()
    {
        ArrayList<Ticket> result = new ArrayList<Ticket>();
        for(Group group:groups)
        {
            result.addAll(group.getTickets().stream().filter((t) -> (userType == UserType.Worker)?t.getAssignees().contains(this):t.getAssigner().equals(this)).collect(Collectors.toList()));
        }
        return result;
    }
}
