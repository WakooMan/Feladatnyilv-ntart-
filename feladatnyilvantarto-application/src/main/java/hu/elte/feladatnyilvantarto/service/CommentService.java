package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Comment;
import hu.elte.feladatnyilvantarto.domain.NotificationFactory;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.CommentRepository;
import hu.elte.feladatnyilvantarto.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private CommentRepository commentRepository;

    public void addComment(User from,User tagged, Ticket ticket, String message){
        Comment comment=new Comment(from, ticket, message);
        comment.setTaggedUser(tagged);
        comment.setDate(LocalDateTime.now());
        commentRepository.save(comment);
        notificationRepository.save(new NotificationFactory().createCommentNotification(tagged,comment));
    }


}
