package com.codegym.service.friend;

import com.codegym.model.entity.Friend;
import com.codegym.repository.IFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
    public Iterable<Long> getMutualFriend(Long userId1, Long userId2) {
        return friendRepository.getMutualFriend(userId1, userId2);
    }
}
