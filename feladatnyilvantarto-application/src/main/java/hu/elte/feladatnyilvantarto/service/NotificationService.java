package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.domain.Notification;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> GetNotificationsByUser(User user)
    {
        return notificationRepository.findNotificationsByUserAndSeenFalseOrderByDateDesc(user);
    }

}
