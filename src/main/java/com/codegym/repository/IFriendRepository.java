package com.codegym.repository;

import com.codegym.model.entity.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IFriendRepository extends JpaRepository<Friend, Long> {
    @Query(value = "call social_network_3h2t.getAllListFriend(?1)", nativeQuery = true)
    Iterable<Friend> getAllListFriend(Long userId);
}
