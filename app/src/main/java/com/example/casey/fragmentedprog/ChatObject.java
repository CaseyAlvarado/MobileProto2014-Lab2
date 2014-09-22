package com.example.casey.fragmentedprog;

/**
 * Created by casey on 9/20/14.
 */

public class ChatObject {
    //public String sender, message, userId;
    public String message, userId;
    public String time;

    public ChatObject(String message, String userId) {
        //this.sender = sender;
        this.message = message;
        this.userId = userId;
        this.time = String.valueOf(System.currentTimeMillis());
    }

    public ChatObject(String message, String userId, String time) {
        this.message = message;
        this.userId = userId;
        this.time = time;
    }
}
