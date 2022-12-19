package hu.elte.feladatnyilvantarto.transformer;

import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.rest.domain.DashboardTicketResponse;
import hu.elte.feladatnyilvantarto.rest.domain.TicketResponse;
import hu.elte.feladatnyilvantarto.webdomain.form.ModifyTicketForm;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class TicketTransformer {
    public DashboardTicketResponse transformTicketToDashboardTicketResponse(Ticket ticket)
    {
        DashboardTicketResponse response = new DashboardTicketResponse();
        response.setName(ticket.getName());
        response.setDescription(ticket.getDescription());
        response.setPriority(ticket.getPriority());
        response.setDeadline(ticket.getDeadline() != null ?ticket.getDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):"None");
        response.setId(ticket.getId());
        return response;
    }

    public void transformTicketToModifyTicketResponse(ModifyTicketForm modifyticketform,Ticket ticket)
    {
        modifyticketform.setName(ticket.getName());
        modifyticketform.setDescription(ticket.getDescription());
        modifyticketform.setPriority(ticket.getPriority());
        modifyticketform.setId(ticket.getId());
        modifyticketform.setDeadline(ticket.getDeadline().toString());
    }
}
