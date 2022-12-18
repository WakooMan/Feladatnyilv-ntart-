package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Notification;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.service.NotificationService;
import hu.elte.feladatnyilvantarto.service.TimeMeasureService;
import hu.elte.feladatnyilvantarto.webdomain.other.CurrentTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController extends AuthenticatedControllerBase {

    @Autowired
    private TimeMeasureService timeMeasureService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/")
    public String home()
    {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model)
    {
        User user = GetAuthenticatedUser();
        model.addAttribute("timeworkedlastweek", timeMeasureService.timeSpentByUserHoursAndMinutesLastWeek(user));
        model.addAttribute("timeworkedlastmonth", timeMeasureService.timeSpentByUserHoursAndMinutesLastMonth(user));
        model.addAttribute("timeworkedtoday", timeMeasureService.timeSpentByUserToday(user));
        model.addAttribute("fullname",GetAuthenticatedUser().getName());
        model.addAttribute("currentticket",(user.getCurrentTicket()!= null)?new CurrentTicket(user.getCurrentTicket().getName(),"/ticket/"+ user.getCurrentTicket().getId(), user.getCurrentTicket().getId()) : null);
        return "dashboard";
    }

    @PostMapping("/dashboard/removenotification/{id}")
    public String removeNotification(@PathVariable("id") int id)
    {
        Notification notification = notificationService.getNotificationById(id);
        if(notificationService.getNotificationsByUser(GetAuthenticatedUser()).contains(notification))
        {
            notificationService.delete(notification);
        }
        return "redirect:/dashboard";
    }


}
