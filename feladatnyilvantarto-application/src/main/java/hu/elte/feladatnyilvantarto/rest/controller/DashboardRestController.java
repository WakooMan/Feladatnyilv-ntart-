package hu.elte.feladatnyilvantarto.rest.controller;

import hu.elte.feladatnyilvantarto.controller.AuthenticatedControllerBase;
import hu.elte.feladatnyilvantarto.rest.domain.DashboardTicketResponse;
import hu.elte.feladatnyilvantarto.rest.domain.TicketResponse;
import hu.elte.feladatnyilvantarto.service.DashboardService;
import hu.elte.feladatnyilvantarto.transformer.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardRestController extends AuthenticatedControllerBase {

    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private TicketTransformer ticketTransformer;

    @GetMapping("/api/tickets/started/yes")
    public Iterable<DashboardTicketResponse> GetStartedTickets()
    {
        return dashboardService.startedTickets(GetAuthenticatedUser()).stream().map(t -> ticketTransformer.transformTicketToDashboardTicketResponse(t)).toList();
    }

    @GetMapping("/api/tickets/started/no")
    public Iterable<DashboardTicketResponse> GetNotStartedTickets()
    {
        return dashboardService.notStartedTickets(GetAuthenticatedUser()).stream().map(t -> ticketTransformer.transformTicketToDashboardTicketResponse(t)).toList();
    }

    @GetMapping("/api/tickets/weekend")
    public Iterable<DashboardTicketResponse> GetDueInWeekTickets()
    {
        return dashboardService.dueInAWeek(GetAuthenticatedUser()).stream().map(t -> ticketTransformer.transformTicketToDashboardTicketResponse(t)).toList();
    }

    @GetMapping("/api/tickets/created")
    public Iterable<DashboardTicketResponse> GetCreatedTickets()
    {
        return dashboardService.usersTickets(GetAuthenticatedUser()).stream().map(t -> ticketTransformer.transformTicketToDashboardTicketResponse(t)).toList();
    }
}
