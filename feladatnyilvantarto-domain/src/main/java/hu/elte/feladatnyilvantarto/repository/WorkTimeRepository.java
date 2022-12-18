package hu.elte.feladatnyilvantarto.repository;

import hu.elte.feladatnyilvantarto.domain.TimeMeasure;
import hu.elte.feladatnyilvantarto.domain.WorkTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTimeRepository extends CrudRepository<WorkTime, Integer> {

        List<WorkTime> findWorkTimesByTimeMeasure(TimeMeasure tm);

        List<WorkTime> findWorkTimeById(int id);

        WorkTime findWorkTimeByTimeMeasureAndEndDateIsNull(TimeMeasure tm);

}
