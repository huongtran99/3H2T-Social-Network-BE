package com.codegym.repository;

import com.codegym.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "call social_network_3h2t.getAllChatBetweenTwoUser(?1, ?2, ?3)", nativeQuery = true)
    Iterable<Message> getAllHistoryBetweenTwoUser(Long userId1, Long userId2, Integer size);
}
