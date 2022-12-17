package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketListService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findUnassignedTickets(List<Group> groups){
        List<Ticket> unassigned = new ArrayList<>();
        for (Group group : groups) {
            for (Ticket ticket
                    : ticketRepository.findTicketsByGroupOrderByPriorityAsc(group)) {
                if (ticket.getAssignees().isEmpty()) {
                    unassigned.add(ticket);
                }
            }
        }
        return unassigned;
    }
    public List<Ticket> findTicketsByWorker(List<User> users) {
        List<Ticket> workertickets = new ArrayList<>();
        for (User user : users) {
            for (Ticket ticket
            :ticketRepository.findTicketsByAssigneesContainingOrderByPriorityAsc(user)) {
                if (!ticket.getAssignees().isEmpty()) {
                    workertickets.add(ticket);
                }
            }
        }
        return workertickets;
    }

}
