package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Credentials;

public class SignUpService {


    public void signUp(String name, String password, String email,String code){
        Credentials cred = new Credentials();
        cred.setPassword(name);
        cred.setLoginName(name);
        //ezt át kell beszélni
        //User user = new User();
    }
}
