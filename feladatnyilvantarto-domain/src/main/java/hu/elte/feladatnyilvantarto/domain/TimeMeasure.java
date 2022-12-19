package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TimeMeasure {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy = "timeMeasure", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<WorkTime> workTimes = new ArrayList<>();
    @ManyToOne
    private User user;
    @ManyToOne
    private Ticket ticket;
    public TimeMeasure(){
    }
    public TimeMeasure(User user,Ticket ticket)
    {
        this.user=user;
        this.ticket=ticket;
    }
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

    public void setWorkTimes(List<WorkTime> wt){
        workTimes=wt;
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
        if (o instanceof TimeMeasure timeMeasure)
        {
            return  id == timeMeasure.id &&
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
                workTimes.hashCode() +
                user.getId() +
                ticket.getId();
    }
}