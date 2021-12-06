package com.codegym.controller;

import com.codegym.model.entity.Friend;
import com.codegym.model.entity.User;
import com.codegym.service.friend.IFriendService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/friends")
public class FriendRestController {
    @Autowired
    private IFriendService friendService;

    @Autowired
    private IUserService userService;

    @PostMapping("{id}")
    public ResponseEntity<Friend> addFriend(@PathVariable Long id, @RequestBody User sender) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Friend friend = new Friend();
        friend.setSender(sender);
        friend.setReceiver(userOptional.get());
        friend.setStatus(false);
        return new ResponseEntity<>(friendService.save(friend), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Friend> confirm(@PathVariable Long id, @RequestBody User receiver) {
        Optional<Friend> friendOptional = friendService.findFriendBySenderAndReceiver(id, receiver.getId());
        if (!friendOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friendOptional.get().setStatus(true);
        return new ResponseEntity<>(friendService.save(friendOptional.get()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Friend> getFriendSender(@PathVariable Long id, @RequestParam(name = "senderId") Long senderId) {
        Optional<Friend> friendOptional = friendService.findFriendBySenderAndReceiver(senderId, id);
        if (!friendOptional.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(friendOptional.get(), HttpStatus.OK);
    }

    @GetMapping("status/{id}")
    public ResponseEntity<Boolean> getStatus(@PathVariable Long id, @RequestParam(name = "receiver") Long receiver) {
        Optional<Friend> friendOptional = friendService.findFriendBySenderAndReceiver(id, receiver);
        if (!friendOptional.isPresent()) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Friend> deleteFriend(@PathVariable Long id, @RequestParam(name = "senderId") Long senderId) {
        Optional<Friend> friendOptional = friendService.findFriendBySenderAndReceiver(senderId, id);
        if (!friendOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friendService.remove(friendOptional.get().getId());
        return new ResponseEntity<>(friendOptional.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Friend>> getAllFriend(@RequestParam("userId") Long userId) {
        return new ResponseEntity<>(friendService.getAllListFriend(userId), HttpStatus.OK);
    }

    @GetMapping("/mutual")
    public ResponseEntity<Iterable<Long>> getMutualFriend(@RequestParam("userId1") Long userId1,
                                                            @RequestParam("userId2") Long userId2) {
        return new ResponseEntity<>(friendService.getMutualFriend(userId1, userId2), HttpStatus.OK);
    }
}

