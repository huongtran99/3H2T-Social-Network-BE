package com.codegym.controller;

import com.codegym.model.entity.Message;
import com.codegym.service.message.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageRestController {
    @Autowired
    private IMessageService messageService;

    @GetMapping
    public ResponseEntity<Iterable<Message>> getAllMessage(@RequestParam("userId1") Long userId1,
                                        @RequestParam("userId2") Long userId2,
                                        @RequestParam("size") Integer size) {
        Iterable<Message> list = messageService.getAllHistoryBetweenTwoUser(userId1, userId2, size);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Message> createNewMessage(@RequestBody Message message) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        message.setDateTime(date);
        return new ResponseEntity<>(messageService.save(message), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        Optional<Message> messageOptional = messageService.findById(id);
        return messageOptional.map(message -> new ResponseEntity<>(message, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
        Optional<Message> messageOptional = messageService.findById(id);
        return messageOptional.map(message1 -> {
            message.setId(message1.getId());
            return new ResponseEntity<>(messageService.save(message), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Long id) {
        Optional<Message> messageOptional = messageService.findById(id);
        return messageOptional.map(message -> {
            messageService.remove(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }





}
