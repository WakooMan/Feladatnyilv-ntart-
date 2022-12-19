package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.*;
import hu.elte.feladatnyilvantarto.repository.GroupsRepository;
import hu.elte.feladatnyilvantarto.repository.NotificationRepository;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupsService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public List<Group> listGroupsLedByUser(int id){
        return groupsRepository.findGroupsByLeader_Id(id);
    }
    public List<Group> listGroupsOfUser(User user){
        return groupsRepository.findGroupsByWorkersContains(user);
    }
    public List<Group> listExhaustiveGroupsOfUser(User user){
        List<Group> groups=new ArrayList<>();
        groups.addAll(groupsRepository.findGroupsByWorkersContains(user));
        groups.addAll(groupsRepository.findGroupsByLeader_Id(user.getId()));
        return groups;
    }

    public Group getGroupById(int id) {
        return groupsRepository.findById(id).orElse(null);
    }

    public Group createNewGroup(String name, User leader) {
        Group group = new Group();
        group.setGroupName(name);
        group.setLeader(leader);
        return groupsRepository.save(group);
    }

    public void modifyGroupName(User user, Group group, String name){
        if (user.equals(group.getLeader())) {
            group.setGroupName(name);
            groupsRepository.save(group);
        }
    }

    public void addNewMemberByUsername(User user, int groupId, String name) {
        if (usersRepository.findUserByUsername(name) != null) {
            User newMember = usersRepository.findUserByUsername(name);
            Group group = groupsRepository.findGroupById(groupId);
            if (user.equals(group.getLeader()) && newMember != null && !newMember.equals(group.getLeader())) {
                group.addWorker(newMember);
                notificationRepository.save(new NotificationFactory().createGroupNotification(user,group));
                groupsRepository.save(group);
            }
        }
    }

    public void removeMemberById(User user, Group group, int toRemoveid){
        if (user.equals(group.getLeader())){
            group.getWorkers().removeIf(u -> u.getId()==toRemoveid);
            groupsRepository.save(group);
        }
    }
    public void leaveGroup(User user, Group group){
        if (!user.equals(group.getLeader())){
            group.removeWorker(user);
            groupsRepository.save(group);
        }
    }
    public void removeGroup(User user, Group group){
        if (user.equals(group.getLeader())){
            group.getWorkers().removeAll(group.getWorkers());
            List<Notification> notifications = notificationRepository.findAll().stream().filter(
                    n ->
                    {
                        if(n.getType() == NotificationType.GROUP)
                        {
                            GroupNotification notification = ((GroupNotification)n);
                            return notification.getGroup().equals(group);
                        }
                        else if(n.getType() == NotificationType.COMMENT)
                        {
                            CommentNotification notification = ((CommentNotification)n);
                            return notification.getComment().getTicket().getGroup().equals(group);
                        }
                        else if(n.getType() == NotificationType.TICKET)
                        {
                            TicketNotification notification = ((TicketNotification)n);
                            return notification.getTicket().getGroup().equals(group);
                        }
                        else
                        {
                            return false;
                        }
                    }).toList();
            notificationRepository.deleteAll(notifications);
            groupsRepository.delete(group);
        }
    }


}
