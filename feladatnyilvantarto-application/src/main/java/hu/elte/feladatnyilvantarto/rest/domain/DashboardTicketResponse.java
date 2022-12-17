package hu.elte.feladatnyilvantarto.rest.domain;

import hu.elte.feladatnyilvantarto.domain.Priority;

import java.time.LocalDateTime;

public class DashboardTicketResponse {
    private String description;
    private int id;
    private Priority priority;
    private String deadline;

    private boolean started;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean getStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
