package com.codegym.controller;

import com.codegym.model.entity.Friend;
import com.codegym.service.friend.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/friends")
public class FriendRestController {
    @Autowired
    private IFriendService friendService;

    @GetMapping
    public ResponseEntity<Iterable<Friend>> getAllFriend(@RequestParam("userId") Long userId) {
        return new ResponseEntity<>(friendService.getAllListFriend(userId), HttpStatus.OK);
    }
}
