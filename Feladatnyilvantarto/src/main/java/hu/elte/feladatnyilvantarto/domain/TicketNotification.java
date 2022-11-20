package hu.elte.feladatnyilvantarto.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketNotification implements Notification {
    private static final NotificationType NOTIFICATION_TYPE = NotificationType.TICKET;

    private Ticket ticket;
    private String message;
    private final LocalDateTime date;

    public TicketNotification(Ticket ticket) {
        this.ticket = ticket;
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

    public NotificationType getType() {
        return NOTIFICATION_TYPE;
    }
}
