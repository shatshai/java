package com.example.springbootpubsub;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springbootpubsub.Entity.User;
import com.example.springbootpubsub.Repository.UserRepository;
import com.example.springbootpubsub.Service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="/api/users", produces="application/json")
@Tag(name = "User", description = "CRUD For User service")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @Operation(summary = "Get all users", description = "For get all users and by username", tags = { "User" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retrieve all users")})
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get User", description = "For get user and by username", tags = { "User" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retrieve User")})
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        List<User> userList = userRepository.findByUsernameCustomQuery(username);
        if (!userList.isEmpty()) {
            User firstUser = userList.get(0);
            return ResponseEntity.ok(firstUser);
        } else {
            String errorMessage = "User with username " + username + " not found";
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", errorMessage);
            errorResponse.put("status", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping()
    @Operation(summary = "Create User", description = "For create user and publish message to queue", tags = { "User" })
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "User created")})
    public ResponseEntity<Object> createUser(@RequestBody User newUser) {
        // Logic to create user (save to database, etc.)
        // Assume userRepository.save(newUser) saves the user to the database
        userService.saveUser(newUser);

        // Return response with HTTP status 201 (Created) and created user details
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
