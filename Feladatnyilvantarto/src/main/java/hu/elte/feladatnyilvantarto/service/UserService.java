package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Credentials;
import hu.elte.feladatnyilvantarto.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User login(Credentials credentials){
        // meg kell írni még (java security videó kell hozzá
        //és nem itt lesz a login osztály, autorizáció...
        //domain osztály még nem tökéletes, ezért ez se jó még
        return null;
    }
    public void register(String name, String password, String email,String code){
        Credentials cred = new Credentials();
        cred.setPassword(name);
        cred.setLoginName(name);
        //ezt át kell beszélni
        //User user = new User();
    }

}
