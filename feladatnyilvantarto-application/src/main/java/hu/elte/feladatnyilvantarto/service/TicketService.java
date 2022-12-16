package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.*;
import hu.elte.feladatnyilvantarto.repository.TicketRepository;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void createTicket(String name, String description, User assigner,
                               ArrayList<User> assignees, LocalDateTime newdeadline,
                               boolean chekcbox, Group group, Priority priority){
        Ticket ticket = new Ticket();
        ticket.setName(name);
        ticket.setDescription(description);
        ticket.addAssignee(assigner);
        ticket.setAssignees(assignees);
        ticket.setDeadline(newdeadline);
        ticket.setCheckbox(chekcbox);
        ticket.setGroup(group);
        ticket.setPriority(priority.toString());

        ticketRepository.save(ticket);
    }
    public List<Ticket> listTickets(){
        return new ArrayList<>();
    }
    public List<Ticket> listTicketsByGroupsSelected(List<Group> groups){
        return new ArrayList<>();
    }

    public List<Ticket> searchForTicket (String searchWord, List<Group> groups){
        List<Ticket> result=new ArrayList<>();
        for (Group group : groups){
            result.addAll(ticketRepository.findTicketsByGroupAndNameContainingOrderByDate(group, searchWord));
            }
        return result;
        }
    public void closeTicket(Ticket ticket){
        ticket.setCheckbox(false);
        ticketRepository.save(ticket);

    }
    public void removeTicket(Ticket ticket, User user){
        if (ticket.getAssigner().equals(user) || user.equals(ticket.getGroup().getLeader())) {
            ticketRepository.delete(ticket);
        }
    }
    public void modifyDeadline(Ticket ticket, User user, LocalDateTime newdeadline){
        if (newdeadline.isAfter(LocalDateTime.now())) {
            if ((ticket.getAssigner().equals(user)
                    || user.equals(ticket.getGroup().getLeader()))){
                ticket.setDeadline(newdeadline);
                ticketRepository.save(ticket);
            }
        }
    }
    public void modifyDescription(Ticket ticket, User user, String newdescription){
        if ((ticket.getAssigner().equals(user)
                    || user.equals(ticket.getGroup().getLeader()))){
                ticket.setDescription(newdescription);
                ticketRepository.save(ticket);
        }
    }

    public void modifyName(Ticket ticket, User user, String newname){
        if ((ticket.getAssigner().equals(user)
                || user.equals(ticket.getGroup().getLeader()))){
            ticket.setName(newname);
            ticketRepository.save(ticket);
        }
    }
    public void modifyAssignees(Ticket ticket, List<User> assignees, User user){
            if ((ticket.getAssigner().equals(user)
                    || user.equals(ticket.getGroup().getLeader()))){
                ticket.setAssignees((ArrayList<User>) assignees);
                ticketRepository.save(ticket);
            }
        }
    public void unAddSelf(User user, Ticket ticket){
        if ((ticket.getAssignees().contains(user))
                || user.equals(ticket.getGroup().getLeader())){
            ticket.deleteAssignee(user);
            ticketRepository.save(ticket);
        }
    }
    public void setAsCurrent(User user, Ticket ticket){
        user.setCurrentTicket(ticket);
        usersRepository.save(user);
    }
    public void putOnHiatus(User user){
        user.setCurrentTicket(null);
        usersRepository.save(user);
    }
    

}
