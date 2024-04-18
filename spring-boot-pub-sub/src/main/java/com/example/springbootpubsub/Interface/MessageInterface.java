package com.example.springbootpubsub.Interface;

public interface MessageInterface {
    public static final String UPDATE = "update";
    public static final String INSERT = "INSERT";
    public static final String DELETE = "DELETE";
    String getAction();
    String getMessageType();
    int getId();
}
