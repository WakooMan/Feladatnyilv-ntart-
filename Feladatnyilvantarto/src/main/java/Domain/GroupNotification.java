package Domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GroupNotification implements Notification {

    private static final NotificationType NOTIFICATION_TYPE = NotificationType.GROUP;
    private final LocalDateTime date;
    private Group group;
    private String message;


    public GroupNotification(Group group) {
        this.group = group;
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
        message = "New group activity in Group '"
                + group.getGroupName() +
                "'. "
                + date.format(formatter);
        this.message = message;
    }

    public NotificationType getType() {
        return NOTIFICATION_TYPE;
    }

}
