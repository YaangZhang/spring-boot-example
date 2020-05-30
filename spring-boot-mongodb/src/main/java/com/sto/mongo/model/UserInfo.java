package com.sto.mongo.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author zhy
 * @create 2019-11-27-17:02
 */
public class UserInfo implements Serializable {
    /**
     * 用户id
     */
    private String id = UUID.randomUUID().toString().replaceAll("-", "");

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    /**
     * 账户
     */
    private String userName;

    public String getUserName() { return userName; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 密码
     */
    private String passWord;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 昵称
     */
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 邮箱
     */
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * 手机号码
     */
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 会员状态 0：非会员 1：会员
     */
    private int vip = 0;

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    /**
     * 头像
     */
    private String portrait;

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    /**
     * 产品来源
     */
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 匿名状态
     */
    private int anonymous = 1;

    public int getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(int anonymous) {
        this.anonymous = anonymous;
    }

    /**
     * 应用状态
     */
    private int enableStatus = 0;

    public int getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(int enableStatus) {
        this.enableStatus = enableStatus;
    }

    /**
     * 删除状态
     */
    private short deleteStatus = 0;

    public short getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(short deleteStatus) { this.deleteStatus = deleteStatus; }

    /**
     * 渠道来源
     */
    private String channel;

    public String getChannel() { return channel; }

    public void setChannel(String channel) { this.channel = channel; }

    /**
     * 设备版本
     */
    private String version;

    public String getVersion() { return version; }

    public void setVersion(String version) { this.version = version; }

    /**
     * 创建时间
     */
    private long createTime = System.currentTimeMillis();

    public long getCreateTime() { return createTime; }

    public void setCreateTime(long createTime) { this.createTime = createTime; }

    /**
     * 修改时间
     */
    private long modifyTime;

    public long getModifyTime() { return modifyTime; }

    public void setModifyTime(long modifyTime) { this.modifyTime = modifyTime; }

    /**
     * 最近登陆时间
     */
    private long loginTime = System.currentTimeMillis();

    public long getLoginTime() { return loginTime; }

    public void setLoginTime(long loginTime) { this.loginTime = loginTime; }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", nickName='" + nickName + '\'' +
                ", mail='" + mail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", vip=" + vip +
                ", portrait='" + portrait + '\'' +
                ", source='" + source + '\'' +
                ", anonymous=" + anonymous +
                ", enableStatus=" + enableStatus +
                ", deleteStatus=" + deleteStatus +
                ", channel='" + channel + '\'' +
                ", version='" + version + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", loginTime=" + loginTime +
                '}';
    }
}
