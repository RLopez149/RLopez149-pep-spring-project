package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(String Message_Text){
        if ((Message_Text.isBlank() == true || Message_Text.length() > 255)) return null;
        else{
            return null;
        }
    }

    public List<Message> getMessages (){
        return messageRepository.findAll();
    }
}
