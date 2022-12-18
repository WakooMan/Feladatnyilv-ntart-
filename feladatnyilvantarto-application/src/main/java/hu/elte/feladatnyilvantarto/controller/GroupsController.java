package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.service.GroupsService;
import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.service.UserService;
import hu.elte.feladatnyilvantarto.webdomain.form.AddGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GroupsController extends AuthenticatedControllerBase {

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @GetMapping("/groups")
    public String group (Model model)
    {
        List<Group> groupList = groupsService.listGroupsOfUser(GetAuthenticatedUser());
        List<Group> groupListLed = groupsService.listGroupsLedByUser(GetAuthenticatedUser().getId());
        model.addAttribute("username",GetAuthenticatedUser().getUsername());
        boolean hasGroupsled=false;
        boolean hasGroups=false;
        if (groupListLed.size()>0){
            hasGroupsled=true;
        }
        if (groupList.size()>0){
            hasGroups=true;
        }
        model.addAttribute("userHasGroupsLed", hasGroupsled);
        model.addAttribute("userHasGroups", hasGroups);
        model.addAttribute("groupListMember", groupList);
        model.addAttribute("groupListLed", groupListLed);
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
    @PostMapping("/modifygroup/{gid}/action")
    public String modifyGroupAction(AddGroupRequest addNameRequest,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                    @PathVariable("gid") int gid){
        Group group = groupsService.getGroupById(gid);
        if (group != null) {
            groupsService.modifyGroupName(GetAuthenticatedUser(), group, addNameRequest.getName());
        }
        return "redirect:/groups";
    }

    @PostMapping(value="/removemember/action/{id}/{uid}")
    public String removeMemberAction(@PathVariable("id") int id,@PathVariable("uid") int uid, RedirectAttributes redirectAttributes) {
        Group group = groupsService.getGroupById(id);
        if (group != null) {
                groupsService.leaveGroup(GetAuthenticatedUser(), group);
            }
        return "redirect:/groups";
        }




    @PostMapping("/leavegroup/action/{id}")
    public String leaveAction(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Group group = groupsService.getGroupById(id);
        if (group != null) {

            groupsService.leaveGroup(GetAuthenticatedUser(), group);

        }
        return "redirect:/groups";
    }
    @PostMapping("/addgroup/action")
    public String addGroupAction(AddGroupRequest addGroupRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!addGroupRequest.getName().isBlank()){
            groupsService.createNewGroup(addGroupRequest.getName(), GetAuthenticatedUser());}
        return "redirect:/groups";
    }


    @PostMapping("/{gid}/addmember/action")
    public String addMemberAction(AddGroupRequest addUserRequest,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  @PathVariable("gid") int gid){
        groupsService.addNewMemberByUsername(GetAuthenticatedUser(), gid, addUserRequest.getName());
        return "redirect:/groups";
    }
}
