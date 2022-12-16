package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.webdomain.AddGroupRequest;
import hu.elte.feladatnyilvantarto.webdomain.AddTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

public class AddTicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("addTicket")
    public String addTicket(AddTicketRequest addTicketRequest) {
        Ticket ticket = new Ticket();
        ticket.getName();
        ticket.getDescription();

        //nem használom így az AddTicketRequest-et
        //a metódusok nem várnak paramétert, pedig kéne nekik átadni:getName,getDescr., createTicket

        ticketService.createTicket();

        return "redirect:ticket";
    }
}
