package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


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
    @Override
    public void setMessage() {
        message = "You have been assigned to Ticket '"
                + ticket.getName() +
                "'.";
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    @Override
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
