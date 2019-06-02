package com.sto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@NamedQueries({
        @NamedQuery(name = "User.findByPassWord", query = "select u from User u where u.passWord = ?1"),
        @NamedQuery(name = "User.findByNickName", query = "select u from User u where u.nickName = ?1")
})
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false, unique = true)
    private int age;
    @Column(name = "nick_name", nullable = true, unique = true)
    private String nickName;
    @Column(nullable = false)
    private String regTime;
    //省略 getter settet 方法、构造方法


    public User() {
    }

    public User(String userName, String passWord, int age, String regTime) {
        this.userName = userName;
        this.passWord = passWord;
        this.age = age;
        this.regTime = regTime;
    }
}