package com.nedashota.chatsnsapp.dtos;

import lombok.Data;

@Data
public class LoginForm {
    private String mailAddress;
    private String password;

    public LoginForm() {
        super();
    }

    public LoginForm(String mailAddress, String password){
        this.mailAddress = mailAddress;
        this.password = password;
    }
}
