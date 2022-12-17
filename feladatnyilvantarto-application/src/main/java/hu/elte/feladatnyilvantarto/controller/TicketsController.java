package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.service.GroupsService;
import hu.elte.feladatnyilvantarto.service.TicketListService;
import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TicketsController extends AuthenticatedControllerBase {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketListService ticketListService;
    @Autowired
    private GroupsService groupsService;

    @Autowired
    private UserService userService;


    @GetMapping("/tickets")
    public String ticket (Model model)
    {
        List<Group> groupList = groupsService.listGroupsOfUser(GetAuthenticatedUser());
        List<Ticket> ticketList = ticketService.listTicketsByGroup(groupList);

        boolean GroupHasTicket=false;
        if (groupList.size()>0){
            GroupHasTicket=true;
        }
        model.addAttribute("groupHasTickets", GroupHasTicket);
        model.addAttribute("ticketList", ticketList);
        model.addAttribute("username",GetAuthenticatedUser().getName());
        return "tickets";
    }

}
