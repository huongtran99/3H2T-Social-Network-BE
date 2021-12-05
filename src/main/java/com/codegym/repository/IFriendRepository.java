package com.codegym.repository;

import com.codegym.model.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFriendRepository extends JpaRepository<Friend, Long> {
    @Query(value = "select * from friend where sender_id = ? and receiver_id = ?", nativeQuery = true)
    Optional<Friend> findFriendBySenderAndReceiver(Long senderId, Long receiverId);

}
