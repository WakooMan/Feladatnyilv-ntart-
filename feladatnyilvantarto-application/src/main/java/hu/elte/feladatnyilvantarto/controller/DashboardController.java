package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.service.TimeMeasureService;
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
private TicketService ticketService;
    @GetMapping("/")
    public String home(RedirectAttributes model)
    {

        model.addFlashAttribute("fullname",GetAuthenticatedUser().getName());

        model.addFlashAttribute("currentticket",GetAuthenticatedUser().getCurrentTicket());
        return "redirect:dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model)
    {
        model.addAttribute("timeworkedlastweek", timeMeasureService.timeSpentByUserHoursAndMinutesLastWeek(GetAuthenticatedUser()));
        model.addAttribute("timeworkedlastmonth", timeMeasureService.timeSpentByUserHoursAndMinutesLastMonth(GetAuthenticatedUser()));
        model.addAttribute("timeworkedtoday", timeMeasureService.timeSpentByUserToday(GetAuthenticatedUser()));
        model.addAttribute("fullname",GetAuthenticatedUser().getName());
        model.addAttribute("currentticket",(GetAuthenticatedUser().getCurrentTicket()!= null)?new CurrentTicket(GetAuthenticatedUser().getCurrentTicket().getName(),"/ticket/"+ GetAuthenticatedUser().getCurrentTicket().getId()) : null);
        return "dashboard";
    }


}
