package hu.elte.feladatnyilvantarto.controller;

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

        return "redirect:dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model)
    {

        model.addAttribute("fullname",GetAuthenticatedUser().getName());

        return "dashboard";
    }


}
