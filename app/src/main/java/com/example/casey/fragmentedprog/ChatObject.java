package com.example.casey.fragmentedprog;

///**
// * Created by casey on 9/20/14.
// */
//
//public class ChatObject {
//    //public String sender, message, userId;
//    public String message, userId;
//    public String time;
//
//    public ChatObject(String message, String userId) {
//        //this.sender = sender;
//        this.message = message;
//        this.userId = userId;
//        this.time = String.valueOf(System.currentTimeMillis());
//    }
//
//    public ChatObject(String message, String userId, String time) {
//        this.message = message;
//        this.userId = userId;
//        this.time = time;
//    }
//}


import java.io.Serializable;

/**
 * Created by clee2 on 5/26/2014.
 */
public class ChatObject implements Serializable {
    String sender, message, timestamp;

    public ChatObject (String sender, String message, String timestamp) {
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}