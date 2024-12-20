package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.Account;
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
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Account> postNewAccount(@RequestBody Account account){
        if(accountService.checkForUsername(account.getUsername()) != null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        Account newAccount = accountService.registerAccount(account.getUsername(), account.getPassword());
        if(newAccount != null) return new ResponseEntity<>(newAccount, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //TODO: Process user login
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Account> postLogin (@RequestBody Account account){
        Account login = accountService.loginAccount(account.getUsername(), account.getPassword());
        if(login == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else return new ResponseEntity<>(login, HttpStatus.OK);
    }

    //TODO: Process creation of new mesage
    @PostMapping("/messages")
    @ResponseBody
    public ResponseEntity<Message> postNewMessage(@RequestBody Message message){
        if (accountService.checkForId(message.getPostedBy()) == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else{
            Message newMessage = messageService.createMessage(message);
            if(newMessage != null) return new ResponseEntity<>(newMessage, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //TODO: Process of getting all messages
    @GetMapping("/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    //TODO: Process of getting message by messageID
    @GetMapping("/messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Message> getMessageByID(@PathVariable int messageId){
        Message message = messageService.getMessageByMessageID(messageId);
        if(message == null) return new ResponseEntity<>(HttpStatus.OK);
        else  return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //TODO: Process of deleting message by messageID
    @DeleteMapping("/messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Integer> deleteMessageByID(@PathVariable int messageId){
        Integer rows = messageService.deleteMessage(messageId);
        if (rows > 0) return new ResponseEntity<>(rows, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO: Process of updating message by messageID
    @PutMapping("/messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Integer> putMessageByID(@PathVariable int messageId, @RequestBody Message message){
        int rows = messageService.updateMessage(messageId, message);
        if (rows > 0) return new ResponseEntity<>(rows, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //TODO: Process of getting all meassages by accountID
    @GetMapping("/accounts/{accountId}/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getMessagesByAccountID(@PathVariable int accountId){
        List<Message> messages = messageService.getMessagesByAccountID(accountId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
