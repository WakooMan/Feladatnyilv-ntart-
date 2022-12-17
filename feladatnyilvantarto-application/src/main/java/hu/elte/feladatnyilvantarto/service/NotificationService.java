package hu.elte.feladatnyilvantarto.service;

import hu.elte.feladatnyilvantarto.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;


}
