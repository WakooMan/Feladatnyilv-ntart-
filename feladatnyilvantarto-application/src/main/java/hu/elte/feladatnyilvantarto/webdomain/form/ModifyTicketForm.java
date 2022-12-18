package hu.elte.feladatnyilvantarto.webdomain.form;

import hu.elte.feladatnyilvantarto.domain.Priority;

import javax.validation.constraints.NotEmpty;

public class ModifyTicketForm {
    private int id;
    @NotEmpty(message = "Please give me the ticket's name!")
    private String name;
    @NotEmpty(message = "Please give me a description!")
    private String description;
    private Priority priority;
    @NotEmpty(message = "Please give me the deadline of the ticket!")
    private String deadline;

    public ModifyTicketForm(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
