package com.sto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class User implements Serializable {
    private Long id;
    private String userName;
    private String passWord;
    private String userSex;
    private String nikeName;

    public User() {
    }

    public User(String userName, String passWord, String userSex) {
        this.userName = userName;
        this.passWord = passWord;
        this.userSex = userSex;
    }

    public User(String userName, String passWord, String userSex, String nikeName) {
        this.userName = userName;
        this.passWord = passWord;
        this.userSex = userSex;
        this.nikeName = nikeName;
    }

    public User(Long id, String userName, String password, String userSex, String nikeName) {
        this.id = id;
        this.userName = userName;
        this.passWord = password;
        this.userSex = userSex;
        this.nikeName = nikeName;
    }

}