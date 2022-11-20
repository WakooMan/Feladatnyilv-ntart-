package hu.elte.feladatnyilvantarto.repository;

import hu.elte.feladatnyilvantarto.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface GroupsRepository extends PagingAndSortingRepository<Group, Integer> {
}
