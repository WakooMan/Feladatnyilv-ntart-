package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.util.List;

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
    public boolean equals(Object o)
    {
        if (o instanceof TimeMeasure)
        {
            TimeMeasure timeMeasure = (TimeMeasure) o;
            return  id == timeMeasure.id &&
                    workState.equals(timeMeasure.workState) &&
                    workTimes.equals(timeMeasure.workTimes) &&
                    user.getId() == timeMeasure.user.getId() &&
                    ticket.getId() == timeMeasure.getId();
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return  id +
                workState.hashCode() +
                workTimes.hashCode() +
                user.getId() +
                ticket.getId();
    }
}