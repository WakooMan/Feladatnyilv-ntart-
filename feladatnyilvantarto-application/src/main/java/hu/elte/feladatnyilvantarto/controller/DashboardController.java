package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.service.NotificationService;
import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.service.TimeMeasureService;
import hu.elte.feladatnyilvantarto.service.UserService;
import hu.elte.feladatnyilvantarto.webdomain.other.CurrentTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DashboardController extends AuthenticatedControllerBase {

    @Autowired
    private TimeMeasureService timeMeasureService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;
@Autowired
private TicketService ticketService;
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
        model.addAttribute("currentticket",(user.getCurrentTicket()!= null)?new CurrentTicket(user.getCurrentTicket().getName(),"/ticket/"+ user.getCurrentTicket().getId()) : null);
        model.addAttribute("notifications",notificationService.GetNotificationsByUser(user));
        return "dashboard";
    }


}
