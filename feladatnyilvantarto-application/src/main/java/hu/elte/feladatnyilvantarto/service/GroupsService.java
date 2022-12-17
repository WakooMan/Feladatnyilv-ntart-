package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.GroupsRepository;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupsService {

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
            if (user.equals(group.getLeader()) && newMember != null) {
                group.addWorker(newMember);
                //NOTIFICATION
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
            groupsRepository.delete(group);
        }
    }


}
