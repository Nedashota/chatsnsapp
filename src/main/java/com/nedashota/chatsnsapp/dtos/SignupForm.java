package com.nedashota.chatsnsapp.dtos;

import lombok.Data;
import com.nedashota.chatsnsapp.entities.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
public class SignupForm {
    private String name;

    private String mailAddress;

    private String password;

    private MultipartFile icon;

    public User toUser() throws IOException {
        return new User(this.name, this.mailAddress, this.password, this.icon.getBytes());
    }
}
