package hu.elte.feladatnyilvantarto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TicketController {
    @GetMapping("/ticket/{id}")
    public String GetTicket(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("id",id);
        return "ticket";
    }
}
