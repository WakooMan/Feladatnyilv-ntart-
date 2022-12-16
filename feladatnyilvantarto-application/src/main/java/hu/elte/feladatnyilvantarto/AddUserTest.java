package hu.elte.feladatnyilvantarto;



import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.GroupsRepository;
import hu.elte.feladatnyilvantarto.repository.TicketRepository;
import hu.elte.feladatnyilvantarto.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
         users.add(lead3);
         Group g1= new Group("A-team", lead1);
         Group g2= new Group("B-team", lead2);
         Group g3= new Group("Z-team", lead3);
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
         g3.addWorker(u7);
         User u8=new User("Újházi Johanna", "a6",pwdEncoder.encode("a6"));
         g3.addWorker(u8);
         User u9=new User("Inas Judit", "aw",pwdEncoder.encode("aw"));
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
         ArrayList<Group> groups = new ArrayList<>();
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
