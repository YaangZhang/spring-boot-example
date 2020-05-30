package com.sto.es;

import com.sto.es.entities.Post;
import com.sto.es.service.ElasticService;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhy
 * @create 2020-04-14-11:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com.sto.es/applicationContext.xml")
public class PostServiceTest {

    @Autowired
    private ElasticService elasticService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    // 创建索引和映射
    @Test
    public void createIndex() {
        elasticsearchTemplate.createIndex(Post.class);
        elasticsearchTemplate.putMapping(Post.class);
    }

    @Test
    public void testSave() throws Exception{
        elasticService.saveOne();

//        elasticsearchTemplate.getClient().admin().indices().prepareDelete("program").execute().actionGet();
//        elasticsearchTemplate.getClient().admin().indices().prepareCreate("program").execute().actionGet();

        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("id").field("type", "text").field("store", "true").field("index", "true").endObject()
                .startObject("source").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("title").field("type", "text").field("store", "true").field("index", "false").endObject()
                .startObject("alias").field("type", "text").field("store", "true").field("index", "false").endObject()
                .startObject("summary").field("type", "text").field("store", "true").field("index", "false").endObject()
                .startObject("assetType").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("areas").field("type", "text").field("store", "true").field("index", "true").endObject()
                .startObject("release").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("originPoints").field("type", "text").field("store", "true").field("index", "true").endObject()
                .startObject("assetCategories").field("type", "text").field("store", "true").field("index", "true").endObject()
                .startObject("categoryIds").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("messageStatus").field("type", "text").field("store", "true").field("index", "true").endObject()
                .startObject("auditStatus").field("type", "text").field("store", "true").field("index", "true").endObject()
                .startObject("pay").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("copyright").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("sensitive").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("playMode").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("searchMode").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("downMode").field("type", "integer").field("store", "true").field("index", "true").endObject()
                .startObject("createTime").field("type", "long").field("store", "true").field("index", "true").endObject()
                .startObject("publishTime").field("type", "long").field("store", "true").field("index", "true").endObject()
                .endObject()
                .endObject();
        PutMappingRequest request = Requests.putMappingRequest("program").type("programContent").source(mapping);
        elasticsearchTemplate.getClient().admin().indices().putMapping(request).actionGet();


    }
}
