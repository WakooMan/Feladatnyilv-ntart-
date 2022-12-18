package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User userFrom;
    @ManyToOne
    private Ticket ticket;
    private String message;
    private LocalDateTime date;
    @OneToOne (fetch = FetchType.EAGER)
    private User taggedUser;

    public Comment(User from, Ticket in, String message) {
        this.userFrom = from;
        this.ticket = in;
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
    public Ticket getTicket(){
        return ticket;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public Ticket getIn() {
        return ticket;
    }

    public void setIn(Ticket tck) {
        ticket = tck;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public void setTicket(Ticket ticketIn) {
        this.ticket = ticketIn;
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
    public User getTaggedUser() {
        return taggedUser;
    }

    public void setTaggedUser(User taggedUser) {
        this.taggedUser = taggedUser;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Comment comment)
        {
            return  id == comment.id &&
                    userFrom.getId() == comment.userFrom.getId() &&
                    ticket.getId() == comment.ticket.getId() &&
                    message.equals(comment.message) &&
                    date.equals(comment.date) &&
                    taggedUser.equals(comment.taggedUser);
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return  id +
                userFrom.getId() +
                ticket.getId() +
                message.hashCode() +
                date.hashCode() +
                taggedUser.hashCode();
    }
}

