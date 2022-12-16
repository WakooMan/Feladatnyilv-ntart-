package hu.elte.feladatnyilvantarto.rest.domain;

import hu.elte.feladatnyilvantarto.domain.*;

import java.time.LocalDateTime;
import java.util.List;

public class TicketResponse {
    private int id;
    private String name;
    private String description;
    private User assigner;
    private List<User> assignees;
    private Priority priority;
    private LocalDateTime deadline;
    private LocalDateTime date;
    private boolean checkbox;
    private Group group;
    private List<Comment> comments;
    private List<TimeMeasure> timeMeasures;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public List<User> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<User> assignees) {
        this.assignees = assignees;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<TimeMeasure> getTimeMeasures() {
        return timeMeasures;
    }

    public void setTimeMeasures(List<TimeMeasure> timeMeasures) {
        this.timeMeasures = timeMeasures;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
