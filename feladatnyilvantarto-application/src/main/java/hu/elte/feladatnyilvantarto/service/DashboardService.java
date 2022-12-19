package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Notification;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.NotificationRepository;
import hu.elte.feladatnyilvantarto.repository.TicketRepository;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> startedTickets(User user){
        return ticketRepository.findTicketsByAssigneesContainingAndTimeMeasuresIsNotNullOrderByPriorityAsc(user).stream().filter(t-> !t.getCheckbox()).toList();
    }
    public List<Ticket> notStartedTickets(User user){
        return ticketRepository.findTicketsByAssigneesContainingAndTimeMeasuresIsNullOrderByPriorityAsc(user).stream().filter(t-> !t.getCheckbox()).toList();
    }
    public List<Ticket> dueInAWeek(User user) {
        List<Ticket> deadlines = ticketRepository.findTicketsByAssigneesContainingOrderByDeadlineAsc(user).stream().filter(t -> !t.getCheckbox()).toList();

        deadlines.removeIf(ticket -> ticket.getDeadline() == null || ticket.getDeadline().isAfter(ticket.getDeadline().plusDays(7)));
        return deadlines;
    }
    public List<Ticket> usersTickets(User user){
        return ticketRepository.findTicketsByAssigner(user);
    }


}
