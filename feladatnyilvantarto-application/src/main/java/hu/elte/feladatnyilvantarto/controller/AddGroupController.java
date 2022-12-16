package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.service.GroupsService;
import hu.elte.feladatnyilvantarto.webdomain.AddGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddGroupController {

    @Autowired
    private GroupsService groupsService;

    @PostMapping("addGroup")
    public String addGroup(AddGroupRequest addGroupRequest) {
        Group group = new Group();
        group.setGroupName(addGroupRequest.getName());
        group.setLeader(addGroupRequest.getLeader());

        groupsService.createNewGroup(group);

        return "redirect:groups";
    }
}
