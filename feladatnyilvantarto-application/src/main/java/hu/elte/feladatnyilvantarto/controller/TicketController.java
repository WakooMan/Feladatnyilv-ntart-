package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.service.*;
import hu.elte.feladatnyilvantarto.transformer.TicketTransformer;
import hu.elte.feladatnyilvantarto.webdomain.form.AddGroupRequest;
import hu.elte.feladatnyilvantarto.webdomain.form.AddTicketRequest;
import hu.elte.feladatnyilvantarto.webdomain.form.CommentForm;
import hu.elte.feladatnyilvantarto.webdomain.form.ModifyTicketForm;
import hu.elte.feladatnyilvantarto.webdomain.other.GroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
public class TicketController extends AuthenticatedControllerBase{
    @Autowired
    private CommentService commentService;

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupsService groupsService;
    @Autowired
    private TimeMeasureService timeMeasureService;

    @GetMapping("/ticket/{id}")
    public String GetTicket(@PathVariable("id") int id, Model model)
    {

        Ticket ticket = ticketService.ticketById(id);
        if (ticket==null || !groupsService.listExhaustiveGroupsOfUser(GetAuthenticatedUser()).contains(ticket.getGroup()))
        {
            return "redirect:/tickets";
        }
        Map<String, String> timespent = new HashMap<>();
        for (User user : ticket.getAssignees()) {
            timespent.put(user.getName(), timeMeasureService.timeSpentByUserOnTicket(user, ticket));
        }
        List<User> members= userService.listMembersOfGroupExhaustive(ticket.getGroup().getId());
        List<User> assigned = ticket.getAssignees();
        members.removeAll(assigned);

        model.addAttribute("fullname",GetAuthenticatedUser().getName());
        model.addAttribute("timespent", timespent);
        model.addAttribute("ticket", ticket);
        model.addAttribute("assignees", ticket.getAssignees());
        model.addAttribute("groupMembersNotAssignees", members);
        model.addAttribute("ticketFinished", ticket.getCheckbox());
        model.addAttribute("unassignedEmpty", members.isEmpty());
        model.addAttribute("assigneesEmpty", ticket.getAssignees().isEmpty());
        model.addAttribute("userIsAssigner", ticket.getAssigner().equals(GetAuthenticatedUser()));
        model.addAttribute("userIsGroupLeader", GetAuthenticatedUser().getGroupsLed().contains(ticket.getGroup()));
        model.addAttribute("userIsAssignee", GetAuthenticatedUser().getAssignedTickets().contains(ticket));
        model.addAttribute("userHasCurrentTicketNotThis", (GetAuthenticatedUser().getCurrentTicket()!=null && !GetAuthenticatedUser().getCurrentTicket().equals(ticket)));
        model.addAttribute("userHasCurrentThis", (GetAuthenticatedUser().getCurrentTicket()!=null && GetAuthenticatedUser().getCurrentTicket().equals(ticket)));
        model.addAttribute("UserHasNoCurrent", GetAuthenticatedUser().getCurrentTicket()==null);
        model.addAttribute("uid", GetAuthenticatedUser().getId());
        model.addAttribute("username", GetAuthenticatedUser().getUsername());
        List<User> users = Stream.concat(ticket.getGroup().getWorkers().stream(),Stream.of(ticket.getGroup().getLeader())).filter(w -> !w.equals(GetAuthenticatedUser())).toList();
        model.addAttribute("groupusers",users.stream().map(u -> new GroupUser(u.getId(),u.getName())).toArray());
        if(!model.containsAttribute("commentform"))
        {
            model.addAttribute("commentform",new CommentForm());
        }
        return "ticket";
    }

    @PostMapping("/ticket/writecomment")
    public String WriteComment(@Valid CommentForm commentform, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentform",bindingResult);
            redirectAttributes.addFlashAttribute("commentform",commentform);
            return "redirect:/ticket/" + commentform.getTicketId();
        }
        else
        {
            Ticket ticket = ticketService.ticketById(commentform.getTicketId());
            commentService.addComment(GetAuthenticatedUser(),userService.findUserById(commentform.getTaggedUser()),ticket,commentform.getMessage());
            return "redirect:/ticket/" + commentform.getTicketId();
        }
    }

    @PostMapping("/ticket/startaction/{id}")
    public String StartTicket (@PathVariable("id") int id) {
        Ticket ticket = ticketService.ticketById(id);
        if(GetAuthenticatedUser().getCurrentTicket()==null
        && ticket.getAssignees().contains(GetAuthenticatedUser())){
            timeMeasureService.startWorkOnTicket(GetAuthenticatedUser(),ticket);
            userService.setTicketAsCurrent(ticket, GetAuthenticatedUser());}

        return "redirect:/dashboard";
    }

    @PostMapping("/ticket/restart/{id}")
    public String ReStartTicket (@PathVariable("id") int id) {
        Ticket ticket = ticketService.ticketById(id);
        if (ticket.getGroup().getLeader().equals(GetAuthenticatedUser()) || ticket.getAssignees().contains(GetAuthenticatedUser())) {
            ticketService.restartTicket(GetAuthenticatedUser(), ticket);
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/ticket/pauseaction/{id}")
    public String PauseTicket (@PathVariable("id") int id) {
        Ticket ticket = ticketService.ticketById(id);
        if(GetAuthenticatedUser().getCurrentTicket().equals(ticket)
                && ticket.getAssignees().contains(GetAuthenticatedUser())){
            timeMeasureService.pauseWorkOnTicket(GetAuthenticatedUser(),ticket);
        userService.unsetTicketAsCurrent(ticket, GetAuthenticatedUser());}
        return "redirect:/dashboard";


    }
    @GetMapping("/ticket/modifyticket/{id}")
    public String form(Model model, @PathVariable("id")int id)
    {
        if(!model.containsAttribute("modifyticketform")) {
            Ticket ticket = ticketService.ticketById(id);
            ModifyTicketForm modifyticketform = new ModifyTicketForm();
            new TicketTransformer().transformTicketToModifyTicketResponse(modifyticketform,ticket);
            model.addAttribute("modifyticketform", modifyticketform);
        }
        model.addAttribute("fullname",GetAuthenticatedUser().getName());
        return "modifyticket";
    }

    @PostMapping("/ticket/modifyticket")
    public String ModifyTicket(@Valid ModifyTicketForm modifyticketform,BindingResult bindingResult,RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modifyticketform",bindingResult);
            redirectAttributes.addFlashAttribute("modifyticketform",modifyticketform);
            return "redirect:/ticket/modifyticket/" + modifyticketform.getId();
        }
        Ticket ticket = ticketService.ticketById(modifyticketform.getId());
        ticketService.modifyTicket(ticket,GetAuthenticatedUser(),modifyticketform.getName(),modifyticketform.getDescription(),modifyticketform.getPriority(),LocalDateTime.parse(modifyticketform.getDeadline()));
        return "redirect:/ticket/" + modifyticketform.getId();
    }
    @PostMapping("/addassignee/action/{tid}")
    public String AddAssignee (AddGroupRequest addUserRequest,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               @PathVariable("tid") int id){
        Ticket ticket = ticketService.ticketById(id);
        User user = userService.findUserByUsername(addUserRequest.getName());
            ticketService.modifyAssignees(ticket,user);
        return "redirect:/ticket/" + id;
    }

    @PostMapping("/ticket/finishaction/{id}")
    public String FinishTicket (@PathVariable("id") int id) {

        Ticket ticket = ticketService.ticketById(id);
        if (ticket.getAssignees().contains(GetAuthenticatedUser()) || ticket.getGroup().getLeader().equals(GetAuthenticatedUser())
                || ticket.getAssigner().equals(GetAuthenticatedUser())){
            ticketService.closeTicket(ticket);

        }
        return "redirect:/dashboard";
    }

    @PostMapping("/removeassignee/action/{tid}")
    public String RemoveAssignee (AddGroupRequest addUserRequest,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               @PathVariable("tid") int id){
        Ticket ticket = ticketService.ticketById(id);
        User user = userService.findUserByUsername(addUserRequest.getName());
        ticketService.removeAssignee(user, ticket);
        return "redirect:/ticket/" + id;
    }

    @PostMapping("/ticket/deleteaction/{id}")
    public String DeleteTicket (@PathVariable("id") int id) {

        Ticket ticket = ticketService.ticketById(id);
        if (ticket.getAssignees().contains(GetAuthenticatedUser()) || ticket.getGroup().getLeader().equals(GetAuthenticatedUser())
                || ticket.getAssigner().equals(GetAuthenticatedUser())){
            ticketService.removeTicket(ticket, GetAuthenticatedUser());
        }
        return "redirect:/tickets";
    }
}
