package com.codegym.controller;

import com.codegym.model.entity.Comment;
import com.codegym.model.entity.Message;
import com.codegym.service.comment.ICommentService;
import com.codegym.service.message.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class WebsocketController {
    @Autowired
    private IMessageService messageService;
    @Autowired
    private ICommentService commentService;

    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public Message connectChat(Message message) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        message.setDateTime(date);
        messageService.save(message);
        return message;
    }

    @MessageMapping("/comments")
    @SendTo("/topic/comment")
    public Comment createComment(Comment comment) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        comment.setDateTime(date);
        return commentService.save(comment);
    }

//    @MessageMapping("/comments")
//    @SendTo("/topic/comment")
//    public Comment updateComment(Long id, Comment comment) {
//        comment.setId(id);
//        return commentService.save(comment);
//    }
//
//    @MessageMapping("/comments")
//    @SendTo("/topic/comment")
//    public void deleteComment(Long id) {
//        commentService.remove(id);
//    }
//
//    @MessageMapping("/comments")
//    @SendTo("/topic/comment")
//    public Optional<Comment> findComment(Long id) {
//        return commentService.findById(id);
//    }
//
//    @MessageMapping("/comments")
//    @SendTo("/topic/comment")
//    public Iterable<Comment> getComment() {
//        return commentService.findAll();
//    }
}
