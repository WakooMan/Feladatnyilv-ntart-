package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.GroupsRepository;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GroupsService {

    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public List<Group> listGroupsLedByUser(User user){
        return groupsRepository.findGroupsByLeader(user);
    }
    public List<Group> listGroupsOfUser(User user){
        return groupsRepository.findGroupsByWorkersContaining(user);
    }


    public void createNewGroup(String name, User user){
        Group group = new Group(name, user);
        groupsRepository.save(group);
    }
    public void modifyGroupName(User user, Group group, String name){
        if (user.equals(group.getLeader())){
            group.setGroupName(name);
            groupsRepository.save(group);
        }
    }
    public void addNewMemberById(User user, Group group, int newUserId){
        if (usersRepository.findUserById(newUserId) != null) {
            User newMember =usersRepository.findUserById(newUserId);
            if (user.equals(group.getLeader()) && newMember != null) {
                group.addWorker(newMember);
                groupsRepository.save(group);
            }
        }
    }

    public void removeMember(User user, Group group, User toRemove){
        if (user.equals(group.getLeader())){
            group.removeWorker(toRemove);
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
        if (!user.equals(group.getLeader())){
            groupsRepository.delete(group);
        }

    }


}
