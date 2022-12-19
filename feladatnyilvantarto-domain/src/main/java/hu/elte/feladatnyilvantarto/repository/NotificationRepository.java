package hu.elte.feladatnyilvantarto.repository;

import hu.elte.feladatnyilvantarto.domain.Notification;
import hu.elte.feladatnyilvantarto.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    Notification findNotificationById(int id);
    List<Notification> findNotificationsByUserOrderByDateDesc(User user);

}