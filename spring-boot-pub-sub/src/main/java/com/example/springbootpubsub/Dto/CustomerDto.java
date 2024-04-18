package com.example.springbootpubsub.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.springbootpubsub.Interface.MessageInterface;;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto implements MessageInterface {
    private String action;
    private Integer id;
    private String name;
    private Integer age;
    private String branch;

    public String getAction() {
        return this.action;
    }

    public String getMessageType() {
        return "customer";
    }

    public int getId() {
        return this.id;
    }
}