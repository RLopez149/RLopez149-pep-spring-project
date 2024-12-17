package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.Message;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    //TODO: Process new user resgestration

    //TODO: Process user login

    //TODO: Process creation of new mesage

    //TODO: Process of getting all messages
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> messages = messageService.getMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    //TODO: Process of getting message by messageID

    //TODO: Process of deleting message by messageID

    //TODO: Process of updating message by messageID

    //TODO: Process of getting all meassages by accountID
}
