package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "NOTIFICATION")
public class CommentNotification implements Notification {

    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private static final NotificationType NOTIFICATION_TYPE = NotificationType.COMMENT;

    @ManyToOne
    private Comment comment;
    private String message;
    private LocalDateTime date;
    @ManyToOne
    private User user;

    public CommentNotification(Comment comm) {
        this.comment = comm;
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
        this.message = message;
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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

}