package hu.elte.feladatnyilvantarto.repository;

import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.TimeMeasure;
import hu.elte.feladatnyilvantarto.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TimeMeasureRepository extends CrudRepository<TimeMeasure, Integer>
{

    List<TimeMeasure> findTimeMeasuresByTicket(Ticket ticket);
    List<TimeMeasure> findTimeMeasuresByUser(User user);
    TimeMeasure findTimeMeasureByUserAndTicket(User user, Ticket ticket);
}
