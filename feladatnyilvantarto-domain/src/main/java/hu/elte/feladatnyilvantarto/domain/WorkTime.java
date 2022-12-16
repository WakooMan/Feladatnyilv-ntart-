package hu.elte.feladatnyilvantarto.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
public class WorkTime {
    @Id
    @GeneratedValue
    private int id;
    private final ZonedDateTime startDate;
    private ZonedDateTime endDate;
    @ManyToOne
    private TimeMeasure timeMeasure;
    private int timeWorked;

    public WorkTime(TimeMeasure timeMeasure){
        this.timeMeasure=timeMeasure;
        startDate= ZonedDateTime.now();
    }

    public WorkTime() {
        startDate= ZonedDateTime.now();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkTime workTime = (WorkTime) o;
        return id == workTime.id && Objects.equals(startDate, workTime.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate);
    }
}
