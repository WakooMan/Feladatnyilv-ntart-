package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import hu.elte.feladatnyilvantarto.service.exceptions.SignUpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UsersRepository usersRepository;
    public void signUp(String name, String password,String userName){
        User usr = usersRepository.findUserByUsername(userName);
        if(usr != null)
        {
            throw new SignUpException();
        }
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setName(name);
        usersRepository.save(user);
    }
}
