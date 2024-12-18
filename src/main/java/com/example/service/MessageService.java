package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //TODO: create message
    public Message createMessage(String Message_Text){
        if ((Message_Text.isBlank() == true || Message_Text.length() > 255)) return null;
        else{
            return null;
        }
    }

    //TODO: retrive all messages
    public List<Message> getAllMessages (){
        return messageRepository.findAll();
    }

    //TODO: retrive a message by message ID
    public Message getMessageByMessageID(int messageID){
        return messageRepository.findById(messageID).orElse(null);
    }

    //TODO: delete message by message ID
    public void deleteMessage(int messageID){
        messageRepository.deleteById(messageID);
    }

    //TODO: update message bu message ID
    
}
