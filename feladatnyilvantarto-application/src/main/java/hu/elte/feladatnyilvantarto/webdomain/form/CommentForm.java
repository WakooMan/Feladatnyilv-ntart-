package hu.elte.feladatnyilvantarto.webdomain.form;

import javax.validation.constraints.NotEmpty;

public class CommentForm {
    private int ticketId;
    private int taggedUser;
    @NotEmpty
    private String message;

    public CommentForm(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTaggedUser() {
        return taggedUser;
    }

    public void setTaggedUser(int taggedUser) {
        this.taggedUser = taggedUser;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}
