package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class CommentNotification extends Notification {

    @Enumerated(EnumType.STRING)
    private static final NotificationType NOTIFICATION_TYPE = NotificationType.COMMENT;

    @ManyToOne
    private Comment comment;
    private String message;

    @Override
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private LocalDateTime date;


    public CommentNotification(Comment comm) {
        this.comment = comm;
        date=comm.getDate();
    }

    public CommentNotification() {

    }

    public Comment getComment() {
        return comment;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        message = "User "
                + comment.getFrom().getName() +
                " added comment in ticket '"
                + comment.getIn().getName() +
                "'. "
                + comment.getDate().format(formatter);
    }

    public LocalDateTime getDate() {
        return comment.getDate();
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return NOTIFICATION_TYPE;
    }


    public void setComment(Comment comment) {
        this.comment = comment;
    }

}