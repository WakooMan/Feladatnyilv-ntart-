package hu.elte.feladatnyilvantarto.webdomain.form;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class AddTicketRequest {
    @NotEmpty(message = "Please give me the name of the Ticket!")
    private String name;
    @NotEmpty(message = "Please give me the description of the Ticket!")
    private String description;


    private int groupId;
    @NotEmpty(message = "Please give me the deadline of the Ticket!")
    private String deadline;
    private String priority;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
