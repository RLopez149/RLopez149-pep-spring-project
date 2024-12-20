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
    public Message createMessage(Message message){
        if ((message.getMessageText().isBlank() || message.getMessageText().length() > 255)) return null;
        else{
            Message newMessage = new Message();
            newMessage.setPostedBy(message.getPostedBy());
            newMessage.setMessageText(message.getMessageText());
            newMessage.setTimePostedEpoch(message.getTimePostedEpoch());
            Message savedMessage = messageRepository.save(newMessage);
            
            return savedMessage;
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
    public int updateMessage(int messageID, Message message){
        if ((message.getMessageText().isBlank() || message.getMessageText().length() > 255)) return 0;
        else {
            Message upMessage = messageRepository.findBymessageId(messageID);

            if(upMessage != null){
                upMessage.setMessageText(message.getMessageText());
                messageRepository.save(upMessage);
                return 1;
            } 
            else return 0;
        }
    }

    //TODO: get list of messages by account ID
    public List<Message> getMessagesByAccountID(int accountID){
        return messageRepository.findBypostedBy(accountID);
    }
}
