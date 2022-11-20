package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name="NOTIFICATION")
public class TicketNotification implements Notification {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private static final NotificationType NOTIFICATION_TYPE = NotificationType.TICKET;

    @ManyToOne
    private Ticket ticket;
    private String message;
    @ManyToOne
    private User user;
    private final LocalDateTime date;

    public TicketNotification(Ticket ticket) {
        this.ticket = ticket;
        date = LocalDateTime.now();
    }

    public TicketNotification() {
        date = LocalDateTime.now();
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        message = "New activity in Ticket '"
                + ticket.getName() +
                "'. "
                + date.format(formatter);
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public NotificationType getType() {
        return NOTIFICATION_TYPE;
    }
}
