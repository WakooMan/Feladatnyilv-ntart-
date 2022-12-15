package hu.elte.feladatnyilvantarto.domain;

public class NotificationFactory {

    public NotificationFactory(){
    }

    /**
     *
     * @param comment Comment
     * @return the commentNotification
     */
    public Notification createCommentNotification(User user,Comment comment){
        return new CommentNotification(user,comment);
    }
    /**
     *
     * @param group Group
     * @return the groupNotification
     */
    public Notification createGroupNotification(User user,Group group){
        return new GroupNotification(user,group);
    }
    /**
     *
     * @param ticket Ticket
     * @return the ticketNotification
     */
    public Notification createTicketNotification(User user,Ticket ticket){
        return new TicketNotification(user,ticket);
    }


}
