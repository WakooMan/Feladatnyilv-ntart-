package hu.elte.feladatnyilvantarto;



import hu.elte.feladatnyilvantarto.domain.Credentials;
import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.GroupsRepository;
import hu.elte.feladatnyilvantarto.repository.TicketRepository;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AddUserTest {
     @Autowired
     private UsersRepository usersRepository;
     @Autowired
    private GroupsRepository groupsRepository;
     @Autowired
     private TicketRepository ticketRepository;

     public void AddUsers(){
         Credentials cred= new Credentials("ab", "ab");
         Credentials cred2= new Credentials("bc", "bc");
         ArrayList<User> users = new ArrayList<>();
         ArrayList<Ticket> tickets = new ArrayList<>();
         User lead1 = new User("Próba Andrea", cred, "Leader" );
         users.add(lead1);
         User lead2 = new User("Teszt Tamás", cred, "Leader");
         users.add(lead2);
         User lead3 = new User("Fukó Mihály", cred, "Leader");
         users.add(lead3);
         Group g1= new Group("A-team", lead1);
         Group g2= new Group("B-team", lead2);
         Group g3= new Group("Z-team", lead3);
         User u1=new User("Bubi Béla", cred, "Worker");
         g1.addWorker(u1);
         User u2=new User("Kovács Patricia", cred2, "Worker");
         g1.addWorker(u2);
         User u3=new User("Torta Donna", cred, "Worker");
         g1.addWorker(u3);
         User u4=new User("Sárga Károly", cred, "Worker");
         g2.addWorker(u4);
         User u5=new User("Vasárnap Zsuzsanna", cred, "Worker");
         g2.addWorker(u5);
         User u6=new User("Kovács Vera", cred, "Worker");
         g2.addWorker(u6);
         User u7=new User("Bokor Kata", cred, "Worker");
         g3.addWorker(u7);
         User u8=new User("Újházi Johanna", cred, "Worker");
         g3.addWorker(u8);
         User u9=new User("Inas Judit", cred, "Worker");
         g3.addWorker(u9);
         Ticket ticket1 = new Ticket(g1, "HIGH");
         ticket1.addAssignee(u1);
         ticket1.setAssigner(lead1);
         ticket1.setGroup(g1);
         ticket1.setName("Open the windows");
         ticket1.setDescription("Please open the windows.");
         Ticket ticket2 = new Ticket(g2, "MEDIUM");
         ticket2.addAssignee(u1);
         ticket2.setAssigner(lead2);
         ticket2.setGroup(g3);
         ticket2.setName("Clean countertops");
         ticket2.setDescription("Please clean the countertops.");
         Ticket ticket3 = new Ticket(g3, "LOW");
         ticket3.addAssignee(u1);
         ticket3.setAssigner(lead3);
         ticket3.setGroup(g3);
         ticket3.setName("Water the office plants");
         ticket3.setDescription("Please water the office plants.");
         Ticket ticket4 = new Ticket(g1, "LOW");
         ticket4.addAssignee(u5);
         ticket4.setAssigner(lead3);
         ticket4.setGroup(g1);
         ticket4.setName("Buy milk");
         ticket4.setDescription("Please buy milk.");
         ArrayList<Group> groups = new ArrayList<Group>();
         groups.add(g1);
         groups.add(g2);
         groups.add(g3);

         usersRepository.saveAll(users);
         groupsRepository.saveAll(groups);
         ticketRepository.save(ticket1);
         ticketRepository.save(ticket2);
         ticketRepository.save(ticket3);
         ticketRepository.save(ticket4);









     }

}
