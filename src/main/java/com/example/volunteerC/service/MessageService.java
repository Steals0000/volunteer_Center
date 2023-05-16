package com.example.volunteerC.service;


import com.example.volunteerC.domain.Message;
import com.example.volunteerC.domain.User;
import com.example.volunteerC.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;


@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;


    public MessageService() {
    }

    public Iterable<Message> checkFiltersInMes(String filter)
    {
        Iterable<Message> messages = messageRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByText(filter);
        } else {
            messages = messageRepo.findAll();
        }
        return messages;
    }

    public Iterable<Message> CreateMessage(User user, String text, String tag)
    {
        Message message = new Message(text, tag, user);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        return messages;
    }
}
