package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.service.CommentService;
import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.service.UserService;
import hu.elte.feladatnyilvantarto.webdomain.form.CommentForm;
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
import java.util.List;
import java.util.stream.Stream;

@Controller
public class TicketController extends AuthenticatedControllerBase{
    @Autowired
    private CommentService commentService;

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    @GetMapping("/ticket/{id}")
    public String GetTicket(@PathVariable("id") int id, Model model)
    {
        Ticket ticket = ticketService.ticketById(id);
        model.addAttribute("ticket", ticket);
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
            commentService.addComment(GetAuthenticatedUser(),Stream.of(userService.findUserById(commentform.getTaggedUser())).toList(),ticket,commentform.getMessage());
            return "redirect:/ticket/" + commentform.getTicketId();
        }
    }

    @PostMapping("/ticket/startaction/{id}")
    public String StartTicket (@PathVariable("id") int id) {
        Ticket ticket = ticketService.ticketById(id);
        ticket.setCheckbox(true);
        return "/ticket/" + id;
    }

    @GetMapping("/ticket/pauseaction/{id}")
    public String PauseTicket (@PathVariable("id") int id) {
        Ticket ticket = ticketService.ticketById(id);
        ticket.setCheckbox(false);
        return "/ticket/" + id;
    }

    @GetMapping("/ticket/finishaction/{id}")
    public String FinishTicket (@PathVariable("id") int id) {
        Ticket ticket = ticketService.ticketById(id);
        ticket.setCheckbox(false);
        return "/ticket/" + id;
    }
}
