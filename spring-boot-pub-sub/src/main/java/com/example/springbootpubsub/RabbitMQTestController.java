package com.example.springbootpubsub;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springbootpubsub.Service.QueueService;
import com.example.springbootpubsub.Dto.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Rabbit-MQ", description = "The Rabbit-MQ test apis.")
public class RabbitMQTestController {

    private final QueueService queueService;

    public RabbitMQTestController(QueueService queueService) {
        this.queueService = queueService;
    }

    @Operation(summary = "Test publish message to queue", description = "For test publish message to queue", tags = { "Rabbit-MQ" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Message published")})
    @PostMapping("/publish")
    public ResponseEntity<Object> publishMessage(@RequestBody CustomerDto customerDto) {
        queueService.publishMessage(customerDto);

        // Return response with HTTP status 200
        Map<String, Object> response = new HashMap<>();
        response.put("message", customerDto);
        response.put("status", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}