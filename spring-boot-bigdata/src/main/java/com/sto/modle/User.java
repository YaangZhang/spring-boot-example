package com.sto.modle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {
    private int id;
    private String userName;
    private String passWord;
    private String email;
    private String nickName;
    private int age;
    private Date regTime;
    private String city;

    public User() {
    }

    public User(int id, String userName, String passWord, String email, String nikeName, Date regTime) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.nickName = nikeName;
        this.regTime = regTime;
    }

    public User(int id, String userName, String passWord, String email, String nikeName, int age, Date regTime, String city) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.nickName = nikeName;
        this.age = age;
        this.regTime = regTime;
        this.city = city;
    }
}