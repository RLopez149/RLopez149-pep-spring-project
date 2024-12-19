package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //TODO: create message
    public Message createMessage(String messageText){
        if ((messageText.isBlank() == true || messageText.length() > 255)) return null;
        else{
            Message message = new Message();
            message.setMessageText(messageText);
            message.getPostedBy();
            message.getTimePostedEpoch();
            return message;
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
    public int deleteMessage(int messageID){
        Optional<Message> message = messageRepository.findById(messageID);

        if(message.isPresent()){
            messageRepository.deleteById(messageID);
            return 1;
        }
        else return 0;
    }

    //TODO: update message by message ID
    public int updateMessage(int messageID, String messageText){
        Optional<Message> messageOpt = messageRepository.findById(messageID);

        if ((messageText.isBlank() == true || messageText.length() > 255 || !messageOpt.isPresent())) return 0;
        else if(messageOpt.isPresent()){
            Message existingMessage = messageOpt.get();
            existingMessage.setMessageText(messageText);
            messageRepository.save(existingMessage);
            return 1;
        }
        else return 0;
    }
}
