package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="GROUPS")
public class Group {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy = "group", cascade = {CascadeType.MERGE})
    private List<Ticket> tickets;
    private String groupName;
    @ManyToOne
    private User leader;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<User> workers;

    public Group() {
    }

    public Group(String groupName, User leader) {
        if (leader.getUserType() != UserType.Leader) {
            throw new InvalidParameterException("Worker cannot be leader in a group!");
        }
        this.leader = leader;
        workers = new ArrayList<User>();
        tickets = new ArrayList<Ticket>();
        this.groupName = groupName;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }


    public User getLeader() {
        return leader;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        if (!tickets.contains(ticket)) {
            tickets.add(ticket);
        }
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public void addWorker(User user) {
        if (user.getUserType() == UserType.Worker && !workers.contains(user)) {
            workers.add(user);
        }
    }

    public void removeWorker(User user) {
        if (user.getUserType() == UserType.Worker) {
            workers.remove(user);
        }
    }
}