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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<Group> groupList = groupsService.listExhaustiveGroupsOfUser(GetAuthenticatedUser());
        List<Ticket> ticketList = ticketService.listTicketsByGroup(groupList);

        boolean GroupHasTicket=false;
        if (groupList.size()>0){
            GroupHasTicket=true;
        }
        model.addAttribute("groups", groupList);
        model.addAttribute("groupHasTickets", GroupHasTicket);
        model.addAttribute("ticketList", ticketList);
        model.addAttribute("username",GetAuthenticatedUser().getName());
        return "tickets";
    }

    @GetMapping("/tickets/filter")
    public String ticket (Model model, @RequestParam Map<String, String> param)
    {

        int group= Integer.parseInt(param.get("group"));
        String status = param.get("status");
        String assignee = param.get("assignee");
        List<Group> groupList = new ArrayList<>();
        if (group==0){
        groupList = groupsService.listExhaustiveGroupsOfUser(GetAuthenticatedUser());}
        else if (groupsService.listExhaustiveGroupsOfUser(GetAuthenticatedUser())
                .contains(groupsService.getGroupById(group))){
            groupList.add(groupsService.getGroupById(group));
        }
        List<Ticket> ticketList = ticketService.listTicketsByGroup(groupList).stream().toList();
        if (status.equals("active")){
            ticketList=ticketList.stream().filter(a -> !a.getCheckbox()).toList();
        }else if (status.equals("finished")){
            ticketList=ticketList.stream().filter(Ticket::getCheckbox).toList();
        }
        if (assignee.equals("user")){
            ticketList=ticketList.stream().filter(a -> a.getAssignees().contains(GetAuthenticatedUser())).toList();
        } else if (assignee.equals("unassigned")){
            ticketList=ticketList.stream().filter(a -> a.getAssignees().isEmpty()).toList();
        } else if (assignee.equals("userAssigner")){
            ticketList=ticketList.stream().filter(a -> a.getAssigner().equals(GetAuthenticatedUser())).toList();
        }

        boolean GroupHasTicket=false;
        if (groupList.size()>0){
            GroupHasTicket=true;
        }
        model.addAttribute("groups", groupList);
        model.addAttribute("groupHasTickets", GroupHasTicket);
        model.addAttribute("ticketList", ticketList);
        model.addAttribute("username",GetAuthenticatedUser().getName());
        return "tickets";
    }

}
