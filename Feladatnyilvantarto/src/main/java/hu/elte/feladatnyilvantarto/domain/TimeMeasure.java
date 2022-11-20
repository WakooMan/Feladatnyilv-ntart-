package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TimeMeasure {
    @Id
    @GeneratedValue
    public int id;
    private WorkState workState;
    @OneToMany(mappedBy = "timeMeasure", orphanRemoval = true)
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

    /* Ezek lehet nem ide kellenek majd: */
    public int sumWorkTimesInMinutes(ArrayList<WorkTime> worklist){
        int sum=0;
        for (WorkTime time : worklist){
            sum+=time.getTimeWorked();
        }
        return sum;
    }

    public String convertMinutesToHoursAndMinutes(int minutes){
        int hours = minutes / 60;
        int mins = minutes % 60;
        return hours + " h, " + mins + " m";
    }

    public String convertMinutesToDaysHoursAndMinutes(int minutes){
        int hours = minutes / 60;
        int mins = minutes % 60;
        int days = hours / 24;
        return days + " d," + hours +" h," + mins +" m";
    }

    public void setUser(User user) {
        this.user = user;
    }
}