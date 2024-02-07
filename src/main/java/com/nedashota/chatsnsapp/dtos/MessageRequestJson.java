package com.nedashota.chatsnsapp.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageRequestJson implements Serializable {
    private Integer myId;
    private Integer peerId;
    private LocalDateTime lastMessageDateTime;

    public MessageRequestJson() {
        super();
    }

    public MessageRequestJson(Integer myId, Integer peerId, LocalDateTime lastMessageDateTime) {
        this.myId = myId;
        this.peerId = peerId;
        this.lastMessageDateTime = lastMessageDateTime;
    }
}
