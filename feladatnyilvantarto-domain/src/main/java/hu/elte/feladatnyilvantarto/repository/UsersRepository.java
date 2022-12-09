package hu.elte.feladatnyilvantarto.repository;


import hu.elte.feladatnyilvantarto.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersRepository extends CrudRepository<User, Integer> {

    User findUserById(int id);


}
