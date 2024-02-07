package com.nedashota.chatsnsapp.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageJson implements Serializable {
    private Integer senderId;
    private Integer receiverId;
    private String message;
    private LocalDateTime sentDateTime;

    public MessageJson() {
        super();
    }

    public MessageJson(Integer senderId, Integer receiverId, String message, LocalDateTime sentDateTime) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.sentDateTime = sentDateTime;
    }
}