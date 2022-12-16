package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.rest.domain.GroupResponse;
import hu.elte.feladatnyilvantarto.rest.domain.UserResponse;
import hu.elte.feladatnyilvantarto.service.GroupsService;
import hu.elte.feladatnyilvantarto.webdomain.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GroupsController {


    public User GetAuthenticatedUser()
    {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
    @GetMapping("/groups")
    public String group (Model model)
    {

        model.addAttribute("username",GetAuthenticatedUser().getUsername());
        return "groups";
    }



}
