package com.sto.es.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * indexName: 索引名称
 * type: 文档的类型
 * @Id: 索引库的id
 * index: 是否进行分词	analyzed:分词	 not_analyzed:不分词  no:不根据此字段进行检索
 * store: 是否存储
 * analyzer: 指定使用的分词器
 * type: 存储数据的类型
 * searchAnalyzer: queryStringQuery就是查询条件进行分词的分词器.
 */
@Document(indexName="projectname",type="post",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class Post {
    @Id
    private String id;
 
    private String title;
 
    private String content;
 
    private int userId;
 
    private int weight;
 
    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", weight=" + weight +
                '}';
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
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
 
    public int getUserId() {
        return userId;
    }
 
    public void setUserId(int userId) {
        this.userId = userId;
    }
 
    public int getWeight() {
        return weight;
    }
 
    public void setWeight(int weight) {
        this.weight = weight;
    }
}