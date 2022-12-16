package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="GROUPS")
public class Group {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy = "group", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Ticket> tickets;
    private String groupName;
    @ManyToOne
    private User leader;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<User> workers;

    public Group() {
    }

    public Group(String groupName, User leader) {

        this.leader = leader;
        workers = new ArrayList<>();
        tickets = new ArrayList<>();
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

            workers.add(user);

    }

    public void removeWorker(User user) {

            workers.remove(user);

    }
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Group group)
        {
            return  id == group.id &&
                    tickets.equals(group.tickets) &&
                    groupName.equals(group.groupName) &&
                    leader.getId() == group.leader.getId() &&
                    workers.stream().map(w -> w.getId()).toList().equals(group.workers.stream().map(w -> w.getId()).toList());
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return  id +
                tickets.hashCode() +
                groupName.hashCode() +
                leader.getId() +
                workers.stream().mapToInt(w -> w.getId()).sum();
    }
}