package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.rest.domain.GroupResponse;
import hu.elte.feladatnyilvantarto.rest.domain.UserResponse;
import hu.elte.feladatnyilvantarto.service.GroupsService;
import hu.elte.feladatnyilvantarto.webdomain.AddGroupRequest;
import hu.elte.feladatnyilvantarto.webdomain.RemoveGroupRequest;
import hu.elte.feladatnyilvantarto.webdomain.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GroupsController extends AuthenticatedControllerBase {

    @Autowired
    private GroupsService groupsService;

    @GetMapping("/groups")
    public String group (Model model)
    {
        List<Group> groupList = groupsService.listGroupsOfUser(GetAuthenticatedUser());
        model.addAttribute("username",GetAuthenticatedUser().getUsername());
        model.addAttribute("groupList", groupList);
        return "groups";
    }

    @PostMapping("/removegroup/action/{id}")
    public String removeGroupAction(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Group group = groupsService.getGroupById(id);
        if (group != null) {
            groupsService.removeGroup(GetAuthenticatedUser(), group);
        }
        return "redirect:/groups";
    }
}
