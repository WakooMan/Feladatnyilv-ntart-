package Domain;

import java.util.ArrayList;

public class Group {
    private ArrayList<Ticket> tickets;
    public Group()
    {
        tickets = new ArrayList<Ticket>();
    }
    public ArrayList<Ticket> getTickets()
    {
        return tickets;
    }
}
