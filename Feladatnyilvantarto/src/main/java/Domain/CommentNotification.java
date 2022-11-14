package Domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentNotification implements Notification {

    private static final NotificationType NOTIFICATION_TYPE = NotificationType.COMMENT;
    private Comment comment;
    private String message;

    public CommentNotification(Comment comm) {
        this.comment = comm;
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

}