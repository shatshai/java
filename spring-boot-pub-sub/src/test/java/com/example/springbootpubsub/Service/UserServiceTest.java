package com.example.springbootpubsub.Service;

import com.example.springbootpubsub.Entity.User;
import com.example.springbootpubsub.Repository.UserRepository;
import com.example.springbootpubsub.Dto.UserDto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private QueueService queueService;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        // Create a sample user for testing
        User newUser = new User();
        newUser.setId(1);
        newUser.setUsername("john.doe");
        newUser.setEmail("tsetSaveUserjohn.doe@example.com");

        // Mock the userRepository.save method to return the same user object
        Mockito.when(userRepository.save(newUser)).thenReturn(newUser);

        // Call the saveUser method
        User savedUser = userService.saveUser(newUser);

        // Verify that the userRepository.save method was called once with the newUser object
        verify(userRepository, Mockito.times(1)).save(newUser);

        // Verify that the queueService.publishMessage method was called once with the correct arguments
        verify(queueService, Mockito.times(1)).publishMessage(any(UserDto.class));
    }
}
