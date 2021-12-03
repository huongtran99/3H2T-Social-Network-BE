package com.codegym.repository;

import com.codegym.model.entity.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFriendRepository extends JpaRepository<Friend, Long> {
    @Query(value = "call social_network_3h2t.getAllListFriend(?1)", nativeQuery = true)
    Iterable<Friend> getAllListFriend(Long userId);

    @Query(value = "select * from friend where sender_id = ? and receiver_id = ?", nativeQuery = true)
    Optional<Friend> findFriendBySenderAndReceiver(Long senderId, Long receiverId);
}
