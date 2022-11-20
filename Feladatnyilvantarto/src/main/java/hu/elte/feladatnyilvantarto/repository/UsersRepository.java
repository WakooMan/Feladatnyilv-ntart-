package hu.elte.feladatnyilvantarto.repository;


import hu.elte.feladatnyilvantarto.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersRepository extends PagingAndSortingRepository<User, Integer> {

}
