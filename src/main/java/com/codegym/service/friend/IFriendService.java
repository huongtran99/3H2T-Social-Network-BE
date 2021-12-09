package com.codegym.service.friend;

import com.codegym.model.entity.Friend;
import com.codegym.model.entity.User;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IFriendService extends IGeneralService<Friend> {

    Iterable<Friend> getAllListFriend(Long userId);

    Optional<Friend> findFriendBySenderAndReceiver(Long senderId, Long receiverId);

    Iterable<Friend> getMutualFriend(Long userId1, Long userId2);
}
