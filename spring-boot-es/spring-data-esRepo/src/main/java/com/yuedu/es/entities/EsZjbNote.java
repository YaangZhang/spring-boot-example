package com.yuedu.es.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author zhy
 * @create 2020-04-09-15:04
 */
@Document(indexName="zjb",type="note")
public class EsZjbNote {
    @Id
    private String f_note_id;
    /**
     * type  	可以是Boolean,Long,Double,Date,String类型。
     * index 	analyzed表示索引，并且对数据进行拆词；not_analyzed表示索引，但是不进行拆词；no表示不索引。
     * analyzer   是字段文本的分词器。默认standard分词器。
     * search_analyzer     是搜索词的分词器。
     * 设置store为true，可以把字段原始值保存，但是索引会更大，需要根据需求使用。
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String f_criticalit;
    @Field
    private String f_start_time;
    @Field
    private String f_create_time;
    @Field
    private String f_attention;
    @Field
    private String f_relation;
 @Field
    private String f_user_id;
    @Field
    private String f_content;
    @Field
    private String f_keyword;
    @Field
    private String f_local;

    public String getF_note_id() {
        return f_note_id;
    }

    public void setF_note_id(String f_note_id) {
        this.f_note_id = f_note_id;
    }

    public String getF_criticalit() {
        return f_criticalit;
    }

    public void setF_criticalit(String f_criticalit) {
        this.f_criticalit = f_criticalit;
    }

    public String getF_start_time() {
        return f_start_time;
    }

    public void setF_start_time(String f_start_time) {
        this.f_start_time = f_start_time;
    }

    public String getF_create_time() {
        return f_create_time;
    }

    public void setF_create_time(String f_create_time) {
        this.f_create_time = f_create_time;
    }

    public String getF_attention() {
        return f_attention;
    }

    public void setF_attention(String f_attention) {
        this.f_attention = f_attention;
    }

    public String getF_relation() {
        return f_relation;
    }

    public void setF_relation(String f_relation) {
        this.f_relation = f_relation;
    }

    public String getF_user_id() {
        return f_user_id;
    }

    public void setF_user_id(String f_user_id) {
        this.f_user_id = f_user_id;
    }

    public String getF_content() {
        return f_content;
    }

    public void setF_content(String f_content) {
        this.f_content = f_content;
    }

    public String getF_keyword() {
        return f_keyword;
    }

    public void setF_keyword(String f_keyword) {
        this.f_keyword = f_keyword;
    }

    public String getF_local() {
        return f_local;
    }

    public void setF_local(String f_local) {
        this.f_local = f_local;
    }

    @Override
    public String toString() {
        return "EsZjbNote{" +
                "f_note_id='" + f_note_id + '\'' +
                ", f_criticalit='" + f_criticalit + '\'' +
                ", f_start_time='" + f_start_time + '\'' +
                ", f_create_time='" + f_create_time + '\'' +
                ", f_attention='" + f_attention + '\'' +
                ", f_relation='" + f_relation + '\'' +
                ", f_user_id='" + f_user_id + '\'' +
                ", f_content='" + f_content + '\'' +
                ", f_keyword='" + f_keyword + '\'' +
                ", f_local='" + f_local + '\'' +
                '}';
    }
}
