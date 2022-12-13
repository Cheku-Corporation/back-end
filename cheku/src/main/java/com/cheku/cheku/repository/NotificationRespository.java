package com.cheku.cheku.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cheku.cheku.auxiliar_classes.UserNotification;
import com.cheku.cheku.model.*;

@Repository
public interface NotificationRespository extends JpaRepository<Notification, Long>  {

    @Query(value = "SELECT priority, subject, message FROM notifications LIMIT 1", nativeQuery = true)
    List<UserNotification> getAllNotifications(Long car_id);
}
