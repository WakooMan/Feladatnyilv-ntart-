package hu.elte.feladatnyilvantarto.domain;

public class NotificationFactory {

    public NotificationFactory(){
    }

    /**
     *
     * @param comment Comment
     * @return the commentNotification
     */
    public Notification createCommentNotification(Comment comment){
        return new CommentNotification(comment);
    }
    /**
     *
     * @param group Group
     * @return the groupNotification
     */
    public Notification createGroupNotification(Group group){
        return new GroupNotification(group);
    }
    /**
     *
     * @param ticket Ticket
     * @return the ticketNotification
     */
    public Notification createTicketNotification(Ticket ticket){
        return new TicketNotification(ticket);
    }


}
