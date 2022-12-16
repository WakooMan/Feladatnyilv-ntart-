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


import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    public User GetAuthenticatedUser()
    {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    @GetMapping("groups")
    public String ListGroups(Model model){
        model.addAttribute("groups", groupsService.listGroupsOfUser(GetAuthenticatedUser()).stream().toList());
        return "groups";
    }

    /*
    public void RemoveGroup(Model model){
        model.addAttribute(groupsService.removeGroup(GetAuthenticatedUser(), group);)
    }
    */


    /*private List<GroupResponse> transformGroupResponse(List<Group> groups) {
        return groups.stream().map(r ->
        {
            GroupResponse rr = new GroupResponse();
            rr.setLeader(r.getLeader().getName());
            rr.setUsers(r.getWorkers().stream().map(w ->
                    {
                        return new UserResponse(w.getName(),
                            w.getAssignedTickets().stream().filter(t ->
                            {return t.getGroup().equals(r);})
                                    .collect(Collectors.toList()).size());
                    }
            ).collect(Collectors.toList()));
            return rr;
        }).collect(Collectors.toList());
    }

    @GetMapping("/groups")
    public String getGroups(Model model){

        Iterable<GroupResponse> groups = transformGroupResponse(GetAuthenticatedUser().getGroups());

        model.addAttribute("username",GetAuthenticatedUser().getUsername());

        return "groups";
    }

     */
}
