package com.cheku.cheku.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cheku.cheku.model.*;

/**
 * The NotificationRespository interface extends the JpaRepository interface and is used to perform
 * CRUD operations on the notification table in the database.
 */
@Repository
public interface NotificationRespository extends JpaRepository<Notification, Long>  {

    @Query(value = "SELECT * FROM notifications WHERE group_id = ?1", nativeQuery = true)
    List<Notification> getAllNotifications(Long group_id);
}
