package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class GroupNotification extends Notification {

    @Enumerated(EnumType.STRING)
    private static final NotificationType NOTIFICATION_TYPE = NotificationType.GROUP;
    private final LocalDateTime date;

    @ManyToOne
    private Group group;
    @ManyToOne
    private User user;
    private String message;


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public GroupNotification(Group group) {
        this.group = group;
        date = LocalDateTime.now();
        setMessage();
    }

    public GroupNotification() {
        date = LocalDateTime.now();
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        message = "You were added to Group '"
                + group.getGroupName() +
                "'. "
                + date.format(formatter);
        this.message = message;
    }

    public NotificationType getType() {
        return NOTIFICATION_TYPE;
    }

}
