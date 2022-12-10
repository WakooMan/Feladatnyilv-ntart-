package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.TimeMeasure;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.TimeMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimeMeasureService {
    @Autowired
    private TimeMeasureRepository timeMeasureRepository;

    public List<TimeMeasure> listTicketTimeMeasures(Ticket ticket){
        return timeMeasureRepository.findTimeMeasuresByTicket(ticket);
    }

    public List<TimeMeasure> listTimeMeasuresByUserInTicket(User user, Ticket ticket){
        return timeMeasureRepository.findTimeMeasuresByUserAndTicket(user, ticket);
    }
    public List<TimeMeasure> listTimeMeasuresByUser(User user){
        return timeMeasureRepository.findTimeMeasuresByUser(user);
    }
    public String timeSpentOnTicketHoursAndMinutes(Ticket ticket){
        List<TimeMeasure> list = timeMeasureRepository.findTimeMeasuresByTicket(ticket);
        return getStringOfTimeHoursAndMinutes(list);
    }
    public String timeSpentOnTicketDaysHoursAndMinutes(Ticket ticket){
        List<TimeMeasure> list = timeMeasureRepository.findTimeMeasuresByTicket(ticket);
        return getStringOfTimeDaysHoursAndMinutes(list);
    }
    public String timeSpentByUserHoursAndMinutes(User user){
        List<TimeMeasure> list = timeMeasureRepository.findTimeMeasuresByUser(user);
        return getStringOfTimeHoursAndMinutes(list);
    }

    private String getStringOfTimeHoursAndMinutes(List<TimeMeasure> list) {
        int a = 0;
        for (TimeMeasure t : list){
            a+=t.sumWorkTimesInMinutes(t.getWorkTimes());
        }
        int hours = a / 60;
        int mins = a % 60;
        return hours + " h, " + mins + " m";
    }
    private String getStringOfTimeDaysHoursAndMinutes(List<TimeMeasure> list) {
        int a = 0;
        for (TimeMeasure t : list){
            a+=t.sumWorkTimesInMinutes(t.getWorkTimes());
        }
        int hours = a / 60;
        int mins = a % 60;
        int days = hours / 24;
        return days + " d," + hours +" h," + mins +" m";
    }

}
