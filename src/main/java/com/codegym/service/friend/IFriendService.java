package com.codegym.service.friend;

import com.codegym.model.entity.Friend;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IFriendService extends IGeneralService<Friend> {

    Iterable<Friend> getAllListFriend(Long userId);

    Optional<Friend> findFriendBySenderAndReceiver(Long senderId, Long receiverId);

    Iterable<Long> getMutualFriend(Long userId1, Long userId2);

}
