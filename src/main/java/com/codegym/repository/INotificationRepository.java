package com.codegym.repository;

import com.codegym.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "select * from Notification where user_id = ?", nativeQuery = true)
    Iterable<Notification> getNotificationByUserId(Long id);
}
