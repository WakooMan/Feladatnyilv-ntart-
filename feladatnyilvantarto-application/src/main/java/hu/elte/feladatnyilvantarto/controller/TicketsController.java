package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.service.GroupsService;
import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TicketsController extends AuthenticatedControllerBase {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private GroupsService groupsService;


    @GetMapping("/tickets")
    public String ticketList (Model model)
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
        model.addAttribute("fullname", GetAuthenticatedUser().getName());
        model.addAttribute("username",GetAuthenticatedUser().getName());
        return "tickets";
    }
    @GetMapping("/tickets/{groupid}")
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
        model.addAttribute("fullname",GetAuthenticatedUser().getName());

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
        ArrayList<Ticket> ticketList=new ArrayList<>();

        if (status.equals("active") && assignee.equals("user")){
            ticketList.addAll(ticketService.listAllTicketsByGroup(groupList).stream().toList()
                    .stream().filter(a -> a.getAssignees().contains(GetAuthenticatedUser()) & !a.getCheckbox()).toList());
        }else if (status.equals("active") && assignee.equals("unassigned")){
            ticketList.addAll(ticketService.listAllTicketsByGroup(groupList).stream().toList()
                    .stream().filter(a -> a.getAssignees().isEmpty() & !a.getCheckbox()).toList());
        }else if (status.equals("active") && assignee.equals("all")){
            ticketList.addAll(ticketService.listAllTicketsByGroup(groupList).stream().toList()
                    .stream().filter(a -> !a.getCheckbox()).toList());
        }else if (status.equals("active") && assignee.equals("userAssigner")){
            ticketList.addAll(ticketService.listAllTicketsByGroup(groupList).stream().toList()
                    .stream().filter(a -> a.getAssigner().equals(GetAuthenticatedUser()) & !a.getCheckbox()).toList());
        }
        else if (status.equals("finished") && assignee.equals("user")){
            ticketList.addAll(ticketService.listAllTicketsByGroup(groupList).stream().toList()
                    .stream().filter(a -> a.getAssignees().contains(GetAuthenticatedUser()) & a.getCheckbox()).toList());
        }else if (status.equals("finished") && assignee.equals("unassigned")){
            ticketList.addAll(ticketService.listAllTicketsByGroup(groupList).stream().toList()
                    .stream().filter(a -> a.getAssignees().isEmpty() & a.getCheckbox()).toList());
        }else if (status.equals("finished") && assignee.equals("all")){
            ticketList.addAll(ticketService.listAllTicketsByGroup(groupList).stream().toList()
                    .stream().filter(a -> a.getCheckbox()).toList());
        }else if (status.equals("finished") && assignee.equals("userAssigner")){
            ticketList.addAll(ticketService.listAllTicketsByGroup(groupList).stream().toList()
                    .stream().filter(a -> a.getAssigner().equals(GetAuthenticatedUser()) & a.getCheckbox()).toList());
        }


        boolean GroupHasTicket=false;
        if (groupList.size()>0){
            GroupHasTicket=true;
        }
        model.addAttribute("groups", groupList);
        model.addAttribute("groupHasTickets", GroupHasTicket);
        model.addAttribute("ticketList", ticketList);
        model.addAttribute("username",GetAuthenticatedUser().getName());
        return "/tickets";
    }

}
