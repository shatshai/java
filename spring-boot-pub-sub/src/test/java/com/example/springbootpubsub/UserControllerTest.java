package com.example.springbootpubsub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.springbootpubsub.Service.UserService;
import com.example.springbootpubsub.Entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        // Create a sample user for testing
        User newUser = new User();
        newUser.setUsername("john.doe");
        newUser.setEmail("john.doe@example.com");

        // Mock the userService.saveUser method to return the same user object
        when(userService.saveUser(any(User.class))).thenReturn(newUser);

        // Call the controller method
        ResponseEntity<Object> response = userController.createUser(newUser);

        // Verify that the userService.saveUser method was called once with the newUser object
        verify(userService, times(1)).saveUser(newUser);

        // Check the response status code
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Check the response body contains the same user object
        assertEquals(newUser, response.getBody());
    }
}