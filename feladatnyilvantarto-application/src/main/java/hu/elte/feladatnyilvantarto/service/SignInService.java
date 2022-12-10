package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Credentials;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    @Autowired
    private UsersRepository usersRepository;
    public User signIn(Credentials credentials){
        User user = null;
        for(User usr : usersRepository.findAll()) {
            if(usr.getCredentials().equals(credentials))
            {
                user = usr;
            }
        }
        if(user == null) {
            throw new SignInException();
        }
        return user;
    }
}
