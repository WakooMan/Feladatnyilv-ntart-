package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.rest.domain.GroupResponse;
import hu.elte.feladatnyilvantarto.rest.domain.UserResponse;
import hu.elte.feladatnyilvantarto.webdomain.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GroupRestController {
    public User GetAuthenticatedUser()
    {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
    private List<GroupResponse> transformGroupResponse(List<Group> groups) {
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

    @GetMapping("/api/groups")
    public Iterable <GroupResponse> getGroups(){

        return transformGroupResponse(GetAuthenticatedUser().getGroups());
    }
}
