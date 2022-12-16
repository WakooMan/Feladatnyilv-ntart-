package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


@Entity

public class TicketNotification extends Notification {
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
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public NotificationType getType() {
        return NOTIFICATION_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketNotification that = (TicketNotification) o;
        return Objects.equals(ticket, that.ticket) && Objects.equals(user, that.user) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket, user, date);
    }
}
