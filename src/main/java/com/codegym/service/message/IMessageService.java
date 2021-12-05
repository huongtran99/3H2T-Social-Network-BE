package com.codegym.service.message;

import com.codegym.model.entity.Message;
import com.codegym.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

public interface IMessageService extends IGeneralService<Message> {
    @Query(value = "call social_network_3h2t.getAllChatBetweenTwoUser(?1, ?2, ?3)", nativeQuery = true)
    Iterable<Message> getAllHistoryBetweenTwoUser(Long userId1, Long userId2, Integer size);
}
