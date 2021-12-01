package com.codegym.service.friend;

import com.codegym.model.entity.Friend;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IFriendService extends IGeneralService<Friend> {
    Optional<Friend> findFriendBySenderAndReceiver(Long senderId, Long receiverId);
}
