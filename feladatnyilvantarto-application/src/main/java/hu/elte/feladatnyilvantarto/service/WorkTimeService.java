package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.TimeMeasure;
import hu.elte.feladatnyilvantarto.domain.WorkTime;
import hu.elte.feladatnyilvantarto.repository.WorkTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTimeService {

    @Autowired
    private WorkTimeRepository workTimeRepository;

    public List<WorkTime> getWorkTimesInTimeMeasure(TimeMeasure tm){
        return workTimeRepository.findWorkTimesByTimeMeasure(tm);
    }


}
