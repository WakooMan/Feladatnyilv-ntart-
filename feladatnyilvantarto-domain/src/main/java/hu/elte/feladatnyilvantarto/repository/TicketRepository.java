package hu.elte.feladatnyilvantarto.repository;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Integer> {

    List<Ticket> findTicketsByAssigneesContainingOrderByPriorityAsc(User user);
    List<Ticket> findTicketsByAssigner(User user);
    List<Ticket> findTicketsByGroupOrderByPriorityAsc(Group groupsSelected);
    List<Ticket> findTicketsByAssigneesContainingOrderByDeadlineAsc(User user);

    List<Ticket> findTicketsByAssigneesContainingOrderByDateDesc(User user);
    List<Ticket> findTicketsByAssigneesContainingAndTimeMeasuresIsNotNullOrderByPriorityAsc(User user);
    List<Ticket> findTicketsByAssigneesContainingAndTimeMeasuresIsNullOrderByPriorityAsc(User user);
    List<Ticket> findTicketsByAssigneesContainingOrderByDateAsc(User user);
    List<Ticket> findTicketsByGroupAndNameContainingOrderByDate(Group group,String searchWord);

}

