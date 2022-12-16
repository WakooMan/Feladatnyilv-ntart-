package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.service.GroupsService;
import hu.elte.feladatnyilvantarto.webdomain.AddGroupRequest;
import hu.elte.feladatnyilvantarto.webdomain.AddTicketRequest;
import hu.elte.feladatnyilvantarto.webdomain.SignUpForm;
import hu.elte.feladatnyilvantarto.webdomain.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;

@Controller
public class AddGroupController extends AuthenticatedControllerBase {

    @Autowired
    private GroupsService groupsService;

    @RequestMapping("/addgroup")
    public String addGroup(Model model) {

        if(!model.containsAttribute("addgroupform")) {
            model.addAttribute("addgroupform", new AddTicketRequest());
        }

        return "addgroup";
    }
    @PostMapping("/addgroup/action")
    public String addGroupAction(AddGroupRequest addGroupRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        groupsService.createNewGroup(addGroupRequest.getName(), GetAuthenticatedUser());
        return "redirect:/groups";
    }

    /*@RequestMapping("addGroup")
    public String addGroup(Model model)
    {
        if(!model.containsAttribute("signupform")) {
            model.addAttribute("signupform", new SignUpForm());
        }
        return "signup";
    }
     */
}
