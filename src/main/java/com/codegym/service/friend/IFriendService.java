package com.codegym.service.friend;

import com.codegym.model.entity.Friend;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IFriendService extends IGeneralService<Friend> {
    @Query(value = "call test_chat.getAllListFriend(?1)", nativeQuery = true)
    Iterable<Friend> getAllListFriend(Long userId);

    @Query(value = "select * from friend where sender_id = ? and receiver_id = ?", nativeQuery = true)
    Optional<Friend> findFriendBySenderAndReceiver(Long senderId, Long receiverId);

}
