package hu.elte.feladatnyilvantarto.domain;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User userFrom;
    @ManyToOne
    private Ticket ticketIn;
    private String message;
    private LocalDateTime date;

    public Comment(User from, Ticket in, String message) {
        this.userFrom = from;
        this.ticketIn = in;
        this.message = message;
        date = LocalDateTime.now();
    }

    public Comment() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public User getFrom() {
        return userFrom;
    }

    public void setFrom(User usr) {
        userFrom = usr;
    }

    public Ticket getIn() {
        return ticketIn;
    }

    public void setIn(Ticket tck) {
        ticketIn = tck;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        message = msg;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}

