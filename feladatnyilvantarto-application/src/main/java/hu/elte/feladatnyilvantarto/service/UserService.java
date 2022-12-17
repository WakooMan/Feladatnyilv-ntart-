package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public User findUserById(int id){
        return usersRepository.findUserById(id);
    }
    public void removeUserFromGroup(){

    }
}
