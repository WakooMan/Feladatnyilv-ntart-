package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;


@Entity

public class TicketNotification extends Notification {
    @Enumerated(EnumType.STRING)
    private static final NotificationType NOTIFICATION_TYPE = NotificationType.TICKET;

    @ManyToOne
    private Ticket ticket;

    public TicketNotification(User user,Ticket ticket) {
        super(user);
        this.ticket = ticket;
        setMessage();
    }

    public TicketNotification() {
        super();
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        message = "New activity in Ticket '"
                + ticket.getName() +
                "'. "
                + getDate().format(formatter);
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public NotificationType getType() {
        return NOTIFICATION_TYPE;
    }
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof TicketNotification notification)
        {
            return  super.equals(o) &&
                    ticket.equals(notification.ticket);
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return  super.hashCode() +
                ticket.hashCode();
    }
}
