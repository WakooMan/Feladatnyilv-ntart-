package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.webdomain.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DashboardController {

    public User GetAuthenticatedUser()
    {
     return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
    @GetMapping("/")
    public String home(RedirectAttributes model)
    {
        model.addFlashAttribute("username",GetAuthenticatedUser().getUsername());

        return "redirect:dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model)
    {

        model.addAttribute("username",GetAuthenticatedUser().getUsername());

        return "dashboard";
    }


}
