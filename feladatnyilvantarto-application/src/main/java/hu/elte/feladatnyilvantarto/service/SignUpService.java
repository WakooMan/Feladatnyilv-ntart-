package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Credentials;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UsersRepository usersRepository;
    public void signUp(String name, String password,String userName){
        Credentials cred = new Credentials(userName,password);
        for(User usr : usersRepository.findAll()){
            if(usr.getCredentials().getLoginName().equals(cred.getLoginName()))
            {
                throw new SignUpException();
            }
        }
        User user = new User();
        user.setCredentials(cred);
        user.setName(name);
        usersRepository.save(user);
    }
}
