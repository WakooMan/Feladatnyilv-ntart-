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
    @OneToMany (fetch = FetchType.EAGER)
    private List<User> taggedUsers;

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

    public User getFrom() {
        return userFrom;
    }

    public void setFrom(User usr) {
        userFrom = usr;
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
    public List<User> getTaggedUsers() {
        return taggedUsers;
    }

    public void setTaggedUsers(List<User> taggedUsers) {
        this.taggedUsers = taggedUsers;
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
                    taggedUsers.equals(comment.taggedUsers);
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
                taggedUsers.hashCode();
    }
}

