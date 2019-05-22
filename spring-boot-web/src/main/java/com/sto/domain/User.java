package com.sto.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 数据校验:Hibernate Validator 是 JSR 规范的具体实现
 *
 * Entity 中不映射成列的字段得加 @Transient 注解，不加注解也会映射成列
 */
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "姓名不能为空")
	private String userName;
	@Column(nullable = false)
	@NotEmpty(message="密码不能为空")
	@Length(min=6,message="密码长度不能小于6位")
	private String passWord;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = true, unique = true)
	private String nickName;
	@Column(nullable = false)
	private String regTime;

	public User() {
		super();
	}
	public User(String email, String userName, String passWord) {
		super();
		this.email = email;
		this.passWord = passWord;
		this.userName = userName;

	}public User(String nickName,String email, String userName, String passWord, String regTime) {
		super();
		this.email = email;
		this.nickName = nickName;
		this.passWord = passWord;
		this.userName = userName;
		this.regTime = regTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", passWord='" + passWord + '\'' +
				", email='" + email + '\'' +
				", nickName='" + nickName + '\'' +
				", regTime='" + regTime + '\'' +
				'}';
	}
}