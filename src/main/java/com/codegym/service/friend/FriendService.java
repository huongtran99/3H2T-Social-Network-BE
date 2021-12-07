package com.codegym.service.friend;

import com.codegym.model.entity.Friend;
import com.codegym.model.entity.User;
import com.codegym.repository.IFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService implements IFriendService{
    @Autowired
    private IFriendRepository friendRepository;

    @Override
    public Iterable<Friend> findAll() {
        return friendRepository.findAll();
    }

    @Override
    public Optional<Friend> findById(Long id) {
        return friendRepository.findById(id);
    }

    @Override
    public Friend save(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public void remove(Long id) {
        friendRepository.deleteById(id);
    }

    @Override
    public Iterable<Friend> getAllListFriend(Long userId) {
        return friendRepository.getAllListFriend(userId);
    }

    @Override
    public Optional<Friend> findFriendBySenderAndReceiver(Long senderId, Long receiverId) {
        return friendRepository.findFriendBySenderAndReceiver(senderId, receiverId);
    }

    @Override
    public Iterable<Friend> getMutualFriend(Long userId1, Long userId2) {
        List<Friend> friendList = new ArrayList<>();
        Iterable<Friend> user1 = getAllListFriend(userId1);
        Iterable<Friend> user2 = getAllListFriend(userId2);
        for (Friend friend1 : user1) {
            for (Friend friend2: user2) {
                if (friend1.getId().equals(friend2.getId()) ) {
                    friendList.add(friend1);
                }
            }
        }
        return friendList;
    }
}
