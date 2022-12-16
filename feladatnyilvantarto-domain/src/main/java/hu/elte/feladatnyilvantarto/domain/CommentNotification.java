package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentNotification that = (CommentNotification) o;
        return Objects.equals(comment, that.comment) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, date);
    }
}