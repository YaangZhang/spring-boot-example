/**
 * Copyright (C), 2015-2020, 优度宽带有限公司
 * FileName: TaskInfo
 * Author:   admin
 * Date:     2020/9/23 11:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.quartz.model;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author admin
 * @create 2020/9/23
 * @since 1.0.0
 */
public class TaskInfo {
    private int id;

    private String osType;

    private String productId;

    private String userId;

    private String title;

    private String content;

    private String extension;

    private String channel;

    private String module;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "id=" + id +
                ", osType='" + osType + '\'' +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", extension='" + extension + '\'' +
                ", channel='" + channel + '\'' +
                ", module='" + module + '\'' +
                '}';
    }
}
