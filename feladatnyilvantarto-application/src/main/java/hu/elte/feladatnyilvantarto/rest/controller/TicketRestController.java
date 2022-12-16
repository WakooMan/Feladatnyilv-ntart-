package hu.elte.feladatnyilvantarto.rest.controller;

import hu.elte.feladatnyilvantarto.rest.domain.TicketResponse;
import hu.elte.feladatnyilvantarto.service.TicketListService;
import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.transformer.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketRestController {
    @Autowired
    private TicketListService ticketListService;
    @Autowired
    private TicketTransformer ticketTransformer;

}
