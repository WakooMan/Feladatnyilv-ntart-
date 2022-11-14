package Domain;

import java.util.ArrayList;

public class TimeMeasure {
    private WorkState workState;
    private ArrayList<WorkTime> workTimes;
    private User user;
    private Ticket ticket;

    public ArrayList<WorkTime> getWorkTimes() {
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
