package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.TimeMeasure;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.domain.WorkTime;
import hu.elte.feladatnyilvantarto.repository.TimeMeasureRepository;
import hu.elte.feladatnyilvantarto.repository.WorkTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeMeasureService {
    @Autowired
    private TimeMeasureRepository timeMeasureRepository;
    @Autowired
    private WorkTimeRepository workTimeRepository;

    public List<TimeMeasure> listTicketTimeMeasures(Ticket ticket){
        return timeMeasureRepository.findTimeMeasuresByTicket(ticket);
    }

    public TimeMeasure listTimeMeasuresByUserInTicket(User user, Ticket ticket){
        return timeMeasureRepository.findTimeMeasureByUserAndTicket(user, ticket);
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

    public String timeSpentByUserHoursAndMinutesLastWeek(User user){
        List<TimeMeasure> list = timeMeasureRepository.findTimeMeasuresByUser(user);
        ArrayList<WorkTime> timelist =new ArrayList<>();
        for (TimeMeasure tm : list){
            for (WorkTime wt : tm.getWorkTimes()){
                if (wt.getEndDate()!=null && wt.getEndDate().isAfter(ZonedDateTime.now().minusDays(7))){
                    timelist.add(wt);
                }else if (wt.getEndDate()==null){
                    wt.end();
                    timelist.add(wt);
                }
            }
        }
        return getStringOfTimeHoursAndMinutesWorkTime(timelist);
    }
    public String timeSpentByUserHoursAndMinutesLastMonth(User user){
        List<TimeMeasure> list = timeMeasureRepository.findTimeMeasuresByUser(user);
        ArrayList<WorkTime> timelist =new ArrayList<>();
        for (TimeMeasure tm : list){
            for (WorkTime wt : tm.getWorkTimes()){
                if (wt.getEndDate()!=null && wt.getEndDate().isAfter(ZonedDateTime.now().minusDays(30))){
                    timelist.add(wt);
                }else if (wt.getEndDate()==null){
                    wt.end();
                    timelist.add(wt);
                }
            }
        }
        return getStringOfTimeHoursAndMinutesWorkTime(timelist);
    }
    public String timeSpentByUserToday(User user){
        List<TimeMeasure> list = timeMeasureRepository.findTimeMeasuresByUser(user);
        ArrayList<WorkTime> timelist =new ArrayList<>();
        for (TimeMeasure tm : list){
            for (WorkTime wt : tm.getWorkTimes()){
                if (wt.getEndDate()!=null && wt.getStartDate().isAfter(ZonedDateTime.now().minusDays(1))){
                    timelist.add(wt);
                }else if (wt.getEndDate()==null){
                    wt.end();
                    timelist.add(wt);
                }
            }
        }
        return getStringOfTimeHoursAndMinutesWorkTime(timelist);
    }

    public String getStringOfTimeHoursAndMinutesWorkTime(List<WorkTime> list) {
        int a = 0;
        for (WorkTime t : list){
            a+=t.getTimeWorked();
        }
        int hours = a / 60;
        int mins = a % 60;
        return hours + " h " + mins + " m";
    }


    public String getStringOfTimeHoursAndMinutes(List<TimeMeasure> list) {
        int a = 0;
        for (TimeMeasure t : list){
            a+=t.sumWorkTimesInMinutes(t.getWorkTimes());
        }
        int hours = a / 60;
        int mins = a % 60;
        return hours + " h " + mins + " m";
    }
    public String getStringOfTimeDaysHoursAndMinutes(List<TimeMeasure> list) {
        int a = 0;
        for (TimeMeasure t : list){
            a+=t.sumWorkTimesInMinutes(t.getWorkTimes());
        }
        int hours = a / 60;
        int mins = a % 60;
        int days = hours / 24;
        return days + " d" + hours +" h" + mins +" m";
    }

    public void startWorkOnTicket(User user, Ticket ticket){
        if (timeMeasureRepository.findTimeMeasureByUserAndTicket(user, ticket)==null){
            TimeMeasure tm = new TimeMeasure();
            tm.setTicket(ticket);
            tm.setUser(user);
            ArrayList<WorkTime> wt = new ArrayList<>();
            tm.setWorkTimes(wt);
            WorkTime wot = new WorkTime();
            wot.setTimeMeasure(tm);
            wot.setEndDate(null);
            timeMeasureRepository.save(tm);
            workTimeRepository.save(wot);
        }else {
            TimeMeasure t = timeMeasureRepository.findTimeMeasureByUserAndTicket(user, ticket);
            WorkTime wt = new WorkTime();
            wt.setTimeMeasure(t);
            wt.setEndDate(null);
            timeMeasureRepository.save(t);
            workTimeRepository.save(wt);

        }
    }

    public void pauseWorkOnTicket(User user, Ticket ticket){
        if (timeMeasureRepository.findTimeMeasureByUserAndTicket(user, ticket)!=null) {
            TimeMeasure t = timeMeasureRepository.findTimeMeasureByUserAndTicket(user, ticket);
            WorkTime wt = workTimeRepository.findWorkTimeByTimeMeasureAndEndDateIsNull(t);
            wt.end();
            timeMeasureRepository.save(t);
            workTimeRepository.save(wt);

        }
    }
}



