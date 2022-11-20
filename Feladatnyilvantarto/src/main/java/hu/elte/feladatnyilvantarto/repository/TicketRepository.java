package hu.elte.feladatnyilvantarto.repository;


import hu.elte.feladatnyilvantarto.domain.Ticket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Integer> {
}
