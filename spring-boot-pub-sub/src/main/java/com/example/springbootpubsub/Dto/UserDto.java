package com.example.springbootpubsub.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.springbootpubsub.Interface.MessageInterface;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements MessageInterface {
    private String action;
    private Integer id;
    private String username;
    private String email;

    public UserDto(String action, Integer id, String username, String email) {
        this.action = action;
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public String getAction() {
        return this.action;
    }

    public String getMessageType() {
        return "user";
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}