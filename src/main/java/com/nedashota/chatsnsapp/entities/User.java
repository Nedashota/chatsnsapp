package com.nedashota.chatsnsapp.entities;

import com.nedashota.chatsnsapp.dtos.LoginForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="mail_address")
    private String mailAddress;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="icon")
    private byte[] icon;

    public User() {
        super();
    }

    public User(String name, String mailAddress, String password, byte[] icon) {
        this.name = name;
        this.mailAddress = mailAddress;
        this.password = password;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return String.format("name:%s, mail address:%s, password:%s", this.name,this.mailAddress, this.password);
    }
    public LoginForm toLoginForm() {
        return new LoginForm(this.mailAddress, this.password);
    }

}
