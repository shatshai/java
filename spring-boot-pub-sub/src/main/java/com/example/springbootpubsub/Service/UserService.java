package com.example.springbootpubsub.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootpubsub.Entity.User;
import com.example.springbootpubsub.Repository.UserRepository;
import com.example.springbootpubsub.Dto.*;
import com.example.springbootpubsub.Interface.MessageInterface;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final QueueService queueService;

    @Autowired
    public UserService(UserRepository userRepository, QueueService queueService) {
        this.userRepository = userRepository;
        this.queueService = queueService;
    }

    public User saveUser(User user) {
        User userSaveResult = userRepository.save(user);
        if (userSaveResult.getId() > 0) {
            // publish message
            UserDto userDto = new UserDto(MessageInterface.INSERT, userSaveResult.getId(), userSaveResult.getUsername(), userSaveResult.getEmail());
            this.queueService.publishMessage(userDto);
        }

        return userSaveResult;
    }
}