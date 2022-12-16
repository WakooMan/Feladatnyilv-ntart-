package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.webdomain.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketsController {
    public User GetAuthenticatedUser()
    {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    @GetMapping("/tickets")
    public String ticket (Model model)
    {
        model.addAttribute("username",GetAuthenticatedUser().getUsername());

        return "tickets";
    }
}
