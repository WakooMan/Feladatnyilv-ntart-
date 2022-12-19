package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

@Entity
public class GroupNotification extends Notification {

    @Enumerated(EnumType.STRING)
    private static final NotificationType NOTIFICATION_TYPE = NotificationType.GROUP;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Group group;

    public GroupNotification(User user,Group group) {
        super(user);
        this.group = group;
        setMessage();
    }

    public GroupNotification() {
        super();
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) { this.group=group;}
    @Override
    public void setMessage() {
        message = "You were added to Group '"
                + group.getGroupName() +
                "'.";
    }
    @Override
    public NotificationType getType() {
        return NOTIFICATION_TYPE;
    }
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof GroupNotification notification)
        {
            return  super.equals(o) &&
                    group.equals(notification.group);
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return  super.hashCode() +
                group.hashCode();
    }

}
