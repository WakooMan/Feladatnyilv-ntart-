package hu.elte.feladatnyilvantarto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketsController extends AuthenticatedControllerBase {

    @GetMapping("/tickets")
    public String ticket (Model model)
    {
        model.addAttribute("fullname",GetAuthenticatedUser().getName());
        return "tickets";
    }
}
