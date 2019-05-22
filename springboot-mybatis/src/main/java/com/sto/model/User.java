package com.sto.model;

import com.sto.enums.UserSexEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nikeName;

    public User() {
    }

    public User(String userName, String passWord, UserSexEnum userSex) {
        this.userName = userName;
        this.passWord = passWord;
        this.userSex = userSex;
    }

    public User(Long id, String userName, String password, UserSexEnum userSex, String nikeName) {
        this.id = id;
        this.userName = userName;
        this.passWord = password;
        this.userSex = userSex;
        this.nikeName = nikeName;
    }

}