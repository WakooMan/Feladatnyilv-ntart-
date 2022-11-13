package Domain;

public class NotificationFactory {

    public NotificationFactory(){
    }

    /**
     *
     * @param a Ticket, Comment or Group
     * @return the according notification class
     */
    public Notification createNotification(Object a){
        if (a == null){
            return null;
        }
        if (a instanceof Comment){
            return new CommentNotification((Comment) a);
        }
        else if (a instanceof Ticket){
            return new TicketNotification((Ticket) a);
        }
        else if (a instanceof Group){
            return new GroupNotification((Group) a);
        }
        return null;
    }


}
