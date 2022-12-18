package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Comment;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> listTicketComments(Ticket ticket){
        return commentRepository.findCommentsByTicketOrderByDateAsc(ticket);
    }

    public Comment findComment(int id){
        return commentRepository.findCommentById(id);
    }
    public void addComment(User from,List<User> tagged, Ticket ticket, String message){
        Comment comment=new Comment(from, ticket, message);
        comment.setTaggedUsers(tagged);
        comment.setDate(LocalDateTime.now());
        commentRepository.save(comment);
    }
    public void removeComment(User from, Ticket ticket){
        Comment comment = commentRepository.findFirstByUserFromAndTicketOrderByDateAsc(from, ticket);
        if (comment.getUserFrom().equals(from) || from.getGroupsLed().contains(comment.getTicket().getGroup())) {
            commentRepository.delete(comment);
        }
    }


}
