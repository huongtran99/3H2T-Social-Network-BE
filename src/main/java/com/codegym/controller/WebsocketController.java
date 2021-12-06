package com.codegym.controller;

import com.codegym.model.entity.Message;
import com.codegym.service.message.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin("*")
@RestController
public class WebsocketController {
    @Autowired
    private IMessageService messageService;

    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public Message connectChat(Message message) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        message.setDateTime(date);
        messageService.save(message);
        return message;
    }
}
