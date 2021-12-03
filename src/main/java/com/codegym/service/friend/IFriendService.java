package com.codegym.service.friend;

import com.codegym.model.entity.Friend;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

public interface IFriendService extends IGeneralService<Friend> {
    @Query(value = "call test_chat.getAllListFriend(?1)", nativeQuery = true)
    Iterable<Friend> getAllListFriend(Long userId);
}
