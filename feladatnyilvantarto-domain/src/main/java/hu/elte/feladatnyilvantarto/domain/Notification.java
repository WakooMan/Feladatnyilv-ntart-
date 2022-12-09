package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public abstract class Notification {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Id
    @GeneratedValue
    private int id;
    private String message;
    private LocalDateTime date;
    private boolean seen=false;

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isSeen() {
        return seen;
    }

    public User getUser() {
        return user;
    }
    @ManyToOne
    private User user;
    public String getMessage(){
        return message;
    }
    public abstract void setMessage();
    public void setUser(User user){
        this.user=user;
    }


    public abstract NotificationType getType();


    public void setSeen() {
        seen=true;
    }
}