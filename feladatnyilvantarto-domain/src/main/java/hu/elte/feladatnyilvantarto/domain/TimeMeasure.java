package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class TimeMeasure {
    @Id
    @GeneratedValue
    private int id;
    private WorkState workState;
    @OneToMany(mappedBy = "timeMeasure", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<WorkTime> workTimes;
    @ManyToOne
    private User user;
    @ManyToOne
    private Ticket ticket;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<WorkTime> getWorkTimes() {
        return workTimes;
    }

    public WorkState getWorkState() {
        return workState;
    }

    public void startWork(){
        workState=WorkState.Working;
    }
    public void pauseWork(){
        workState=WorkState.Paused;
    }

    public User getUser() {
        return user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int sumWorkTimesInMinutes(List<WorkTime> worklist){
        int sum=0;
        for (WorkTime time : worklist){
            sum+=time.getTimeWorked();
        }
        return sum;
    }


    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeMeasure that = (TimeMeasure) o;
        return id == that.id && workState == that.workState && Objects.equals(user, that.user) && Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workState, user, ticket);
    }
}