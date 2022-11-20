package hu.elte.feladatnyilvantarto.domain;


import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class WorkTime {
    private final ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private TimeMeasure timeMeasure;
    private int timeWorked;

    public WorkTime(TimeMeasure timeMeasure){
        this.timeMeasure=timeMeasure;
        startDate= ZonedDateTime.now();
    }
    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void end(){
        endDate=ZonedDateTime.now();
        timeWorked=(int)startDate.until(endDate, ChronoUnit.MINUTES);

    }

    public int getTimeWorked() {
        return timeWorked;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public TimeMeasure getTimeMeasure() {
        return timeMeasure;
    }
}
