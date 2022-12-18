package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.GroupsRepository;
import hu.elte.feladatnyilvantarto.repository.TicketRepository;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private TicketRepository ticketRepository;


    public User findUserById(int id){
        return usersRepository.findUserById(id);
    }
    public User findUserByUsername(String un) { return usersRepository.findUserByUsername(un);}
    public List<User> listMembersOfGroupExhaustive(int id){
        List<User> list = new ArrayList<>();
        Group g = groupsRepository.findGroupById(id);
        list.addAll(g.getWorkers());
        list.add(g.getLeader());
        return list;

    }
    public void setTicketAsCurrent(Ticket t, User user){

            user.setCurrentTicket(t);
            usersRepository.save(user);
            ticketRepository.save(t);

    }

    public void unsetTicketAsCurrent(Ticket t, User user){

            user.setCurrentTicket(null);
            usersRepository.save(user);
        ticketRepository.save(t);

    }

}
