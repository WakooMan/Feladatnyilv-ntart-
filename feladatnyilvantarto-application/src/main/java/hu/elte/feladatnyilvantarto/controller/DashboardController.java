package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.webdomain.other.CurrentTicket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DashboardController extends AuthenticatedControllerBase {


    @GetMapping("/")
    public String home(RedirectAttributes model)
    {
        model.addFlashAttribute("fullname",GetAuthenticatedUser().getName());
        model.addFlashAttribute("currentticket",(GetAuthenticatedUser().getCurrentTicket()!= null)?new CurrentTicket(GetAuthenticatedUser().getCurrentTicket().getName(),"/ticket/"+ GetAuthenticatedUser().getCurrentTicket().getId()) : null);
        return "redirect:dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model)
    {

        model.addAttribute("fullname",GetAuthenticatedUser().getName());
        model.addAttribute("currentticket",(GetAuthenticatedUser().getCurrentTicket()!= null)?new CurrentTicket(GetAuthenticatedUser().getCurrentTicket().getName(),"/ticket/"+ GetAuthenticatedUser().getCurrentTicket().getId()) : null);
        return "dashboard";
    }


}
