package hu.elte.feladatnyilvantarto.rest.controller;

import hu.elte.feladatnyilvantarto.controller.AuthenticatedControllerBase;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.rest.domain.DashboardTicketResponse;
import hu.elte.feladatnyilvantarto.rest.domain.TicketResponse;
import hu.elte.feladatnyilvantarto.service.DashboardService;
import hu.elte.feladatnyilvantarto.service.UserService;
import hu.elte.feladatnyilvantarto.transformer.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardRestController extends AuthenticatedControllerBase {

    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private UserService userService;
    @Autowired
    private TicketTransformer ticketTransformer;

    @GetMapping("/api/tickets/started/yes")
    public Iterable<DashboardTicketResponse> GetStartedTickets()
    {
        User user = GetAuthenticatedUser();
        return dashboardService.startedTickets(user)
                .stream()
                .map(t -> ticketTransformer.transformTicketToDashboardTicketResponse(t))
                .toList();
    }

    @GetMapping("/api/tickets/started/no")
    public Iterable<DashboardTicketResponse> GetNotStartedTickets()
    {
        User user = GetAuthenticatedUser();
        return dashboardService.notStartedTickets(user)
                .stream()
                .map(t -> ticketTransformer.transformTicketToDashboardTicketResponse(t))
                .toList();
    }

    @GetMapping("/api/tickets/weekend")
    public Iterable<DashboardTicketResponse> GetDueInWeekTickets()
    {
        User user = GetAuthenticatedUser();
        return dashboardService.dueInAWeek(user)
                .stream()
                .map(t -> ticketTransformer.transformTicketToDashboardTicketResponse(t))
                .toList();
    }

    @GetMapping("/api/tickets/created")
    public Iterable<DashboardTicketResponse> GetCreatedTickets()
    {
        User user = GetAuthenticatedUser();
        return dashboardService.usersTickets(user)
                .stream()
                .map(t -> ticketTransformer.transformTicketToDashboardTicketResponse(t))
                .toList();
    }
}
