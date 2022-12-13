package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import hu.elte.feladatnyilvantarto.webdomain.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SignInService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}
