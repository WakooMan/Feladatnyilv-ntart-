package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public abstract class Notification {
    @Id
    @GeneratedValue
    private int id;
    protected String message;
    private LocalDateTime date;
    @ManyToOne
    private User user;

    protected Notification(User user) {
        date = LocalDateTime.now();
        this.user = user;
    }

    protected Notification() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public abstract NotificationType getType();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract void setMessage();

    @Override
    public boolean equals(Object o) {
        if (o instanceof Notification notification) {
            return id == notification.id &&
                    message.equals(notification.message) &&
                    date.equals(notification.date) &&
                    user.equals(notification.user);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return id +
                message.hashCode() +
                date.hashCode() +
                user.hashCode();
    }
}
