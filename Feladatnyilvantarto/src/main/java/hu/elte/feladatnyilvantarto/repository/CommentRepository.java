package hu.elte.feladatnyilvantarto.repository;

import hu.elte.feladatnyilvantarto.domain.Comment;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Comment findCommentById(int id);
    List<Comment> findCommentsByTicketOrderByDateAsc(Ticket ticket);
    Comment findFirstByUserFromAndTicketOrderByDateAsc(User from, Ticket ticket);


}
