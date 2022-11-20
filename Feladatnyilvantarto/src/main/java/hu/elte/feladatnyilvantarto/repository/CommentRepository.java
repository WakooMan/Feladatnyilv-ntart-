package hu.elte.feladatnyilvantarto.repository;

import hu.elte.feladatnyilvantarto.domain.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
}
