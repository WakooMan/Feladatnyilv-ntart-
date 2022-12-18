package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

@Entity
public class CommentNotification extends Notification {

    @Enumerated(EnumType.STRING)
    private static final NotificationType NOTIFICATION_TYPE = NotificationType.COMMENT;

    @ManyToOne
    private Comment comment;



    public CommentNotification(User user,Comment comm) {
        super(user);
        this.comment = comm;
        setMessage();
    }

    public CommentNotification() {
    super();
    }
    public Comment getComment() {
        return comment;
    }

    public void setMessage() {
        message = "User "
                + comment.getUserFrom().getName() +
                " added comment in ticket '"
                + comment.getIn().getName() +
                "'.";
    }
    public NotificationType getType() {
        return NOTIFICATION_TYPE;
    }


    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof CommentNotification notification)
        {
            return  super.equals(o) &&
                    comment.equals(notification.comment);
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return  super.hashCode() +
                comment.hashCode();
    }
}