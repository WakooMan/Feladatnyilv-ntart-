package hu.elte.feladatnyilvantarto;



import hu.elte.feladatnyilvantarto.domain.*;
import hu.elte.feladatnyilvantarto.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Component
public class AddUserTest {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TimeMeasureRepository timeMeasureRepository;
    @Autowired
    private WorkTimeRepository workTimeRepository;
    @Autowired
    private PasswordEncoder pwdEncoder;

    public void AddUsers(){
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Ticket> tickets = new ArrayList<>();
        User lead1 = new User("Próba Andrea","a1",pwdEncoder.encode("a1"));
        users.add(lead1);
        User lead2 = new User("Teszt Tamás", "a4",pwdEncoder.encode("a4"));
        users.add(lead2);
        User lead3 = new User("Fukó Mihály", "a2",pwdEncoder.encode("a2"));
        Group g1= new Group("A-team", lead1);
        Group g2= new Group("B-team", lead2);
        User user=new User("Kovács Sziszifusz", "ko",pwdEncoder.encode("ko"));
        g1.addWorker(user);
        User u1=new User("Bubi Béla", "ab",pwdEncoder.encode("ab"));
        g1.addWorker(u1);
        User u2=new User("Kovács Patricia", "bc", pwdEncoder.encode("bc"));
        g1.addWorker(u2);
        User u3=new User("Torta Donna", "test",pwdEncoder.encode("test"));
        g1.addWorker(u3);
        User u4=new User("Sárga Károly", "aa",pwdEncoder.encode("aa"));
        g2.addWorker(u4);
        User u5=new User("Vasárnap Zsuzsanna", "a9",pwdEncoder.encode("a9"));
        g2.addWorker(u5);
        User u6=new User("Kovács Vera", "a8",pwdEncoder.encode("a8"));
        g2.addWorker(u6);
        User u7=new User("Bokor Kata", "a7",pwdEncoder.encode("a7"));
        g2.addWorker(u7);
        User u8=new User("Újházi Johanna", "a6",pwdEncoder.encode("a6"));
        g2.addWorker(u8);
        g2.addWorker(lead3);
        User u9=new User("Inas Judit", "aw",pwdEncoder.encode("aw"));
        g1.addWorker(u9);
        g2.addWorker(user);
        Ticket ticket1 = new Ticket(g1, "HIGH");
        ticket1.addAssignee(u1);
        ticket1.setAssigner(lead1);
        ticket1.setGroup(g1);
        ticket1.setName("Open the windows");
        ticket1.setDescription("Please open the windows.");
        Ticket ticket2 = new Ticket(g2, "MEDIUM");
        ticket2.addAssignee(u1);
        ticket2.setAssigner(lead2);
        ticket2.setGroup(g1);
        ticket2.setName("Clean countertops");
        ticket2.setDescription("Please clean the countertops.");
        Ticket ticket3 = new Ticket(g1, "LOW");
        ticket3.addAssignee(u1);
        ticket3.setAssigner(lead3);
        ticket3.setGroup(g2);
        ticket3.setName("Water my plants");
        ticket3.setDescription("Please water the office plants.");
        Ticket ticketx = new Ticket(g1, "MEDIUM");
        ticketx.setAssigner(user);
        ticketx.setName("Mop the floor");
        ticketx.setDescription("My hand slipped and alas, my dearest yogurt that I have been holding unto as if it were like a babe newborn, spilt like pearls among swine. I implore you to find a hasty solution to this predicament.");
        ticketx.setCheckbox(false);
        ticketx.setDeadline(LocalDateTime.now().plusMonths(1));
        ticketx.addAssignee(u1);
        ticketx.addAssignee(u2);
        Ticket tickety = new Ticket(g1, "HIGH");
        tickety.setAssigner(lead1);
        tickety.setName("Bring my rock back to me");
        tickety.setDescription("I accidentally threw my sweet rock out the window. Find it for me, and bring it to the eleventh floor (the elevator's still not working, so be prepared to take the stairs.)");
        tickety.setCheckbox(true);
        tickety.setDeadline(LocalDateTime.now().minusDays(3));
        tickety.addAssignee(user);
        Ticket ticketz = new Ticket(g1, "HIGH");
        ticketz.setAssigner(lead1);
        ticketz.setName("Fetch me my rock");
        ticketz.setDescription("I accidentally threw my sweet rock out the window again. Find it for me, and bring it to the eleventh floor (the elevator's still not working, so be prepared to take the stairs.)");
        ticketz.setCheckbox(true);
        ticketz.setDeadline(LocalDateTime.now().minusDays(2));
        ticketz.addAssignee(user);
        Ticket ticketu = new Ticket(g1, "HIGH");
        ticketu.setAssigner(lead1);
        ticketu.setName("My rock needs retrieval");
        ticketu.setDescription("I accidentally threw my sweet rock out the window again. Find it for me, and bring it to the eleventh floor (the elevator's still not working, so you'll have to take the stairs.)");
        ticketu.setCheckbox(true);
        ticketu.setDeadline(LocalDateTime.now().minusDays(1));
        ticketu.addAssignee(user);
        Ticket ticketi = new Ticket(g1, "HIGH");
        ticketi.setAssigner(lead1);
        ticketi.setName("Bring back my rock");
        ticketi.setDescription("I accidentally threw my sweet rock out the window again. Find it for me, and bring it to the eleventh floor (the elevator's still not working, so you'll have to take the stairs.)");
        ticketi.setCheckbox(false);
        ticketi.setDeadline(LocalDateTime.now().plusHours(6));
        ticketi.addAssignee(user);
        Ticket ticketv = new Ticket(g1, "HIGH");
        ticketv.setAssigner(lead1);
        ticketv.setName("Find and retrieve my rock");
        ticketv.setDescription("I accidentally threw my sweet rock out the window again. Find it for me, and bring it to the eleventh floor (the elevator's still not working, so you'll have to take the stairs.)");
        ticketv.setCheckbox(false);
        ticketv.setDeadline(LocalDateTime.now().plusDays(1));
        ticketv.addAssignee(user);
        Ticket ticketd = new Ticket(g2, "MEDIUM");
        ticketd.setAssigner(user);
        ticketd.setName("Need new lightbulb");
        ticketd.setDescription("Alas, don't let me linger in darkness!");
        ticketd.setCheckbox(false);
        ticketd.setDeadline(LocalDateTime.now().plusMonths(1));
        ticketd.addAssignee(lead3);
        ticketd.addAssignee(u8);



        Ticket ticket4 = new Ticket(g1, "LOW");
        ticket4.addAssignee(u1);
        ticket4.setAssigner(lead3);
        ticket4.setGroup(g1);
        ticket4.setName("Buy milk");
        ticket4.setDescription("Please buy milk.");
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(g1);
        groups.add(g2);




        usersRepository.saveAll(users);
        groupsRepository.saveAll(groups);
        ticketRepository.save(ticketu);
        ticketRepository.save(ticketi);
        ticketRepository.save(ticketv);
        ticketRepository.save(ticketx);
        ticketRepository.save(tickety);
        ticketRepository.save(ticketz);
        ticketRepository.save(ticketd);
        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);
        ticketRepository.save(ticket3);
        ticketRepository.save(ticket4);
        u1.setCurrentTicket(ticket1);
        usersRepository.save(u1);
        ticket1.setDeadline(LocalDateTime.now().plusDays(5));
        ticketRepository.save(ticket1);













    }

}
