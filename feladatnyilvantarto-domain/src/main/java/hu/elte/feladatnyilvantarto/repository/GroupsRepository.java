package hu.elte.feladatnyilvantarto.repository;

import hu.elte.feladatnyilvantarto.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GroupsRepository extends CrudRepository<Group, Integer> {

    List<Group> findGroupsByLeader_Id(int id);






}
