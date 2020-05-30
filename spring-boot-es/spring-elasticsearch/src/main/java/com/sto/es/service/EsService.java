package com.sto.es.service;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author zhy
 * @create 2020-04-13-18:46
 */
@Service
public class EsService {

    //操作 Elasticsearch 只需要注入 TransportClient 就可以了
    @Autowired
    private TransportClient client;

    public String addCategory(String name) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("id", 11)
                .field("name", name)
                .endObject();
        IndexResponse response = client.prepareIndex("test_db", "product")//修改自己对应的“索引库”和“表”：test_db/product
                .setSource(builder)
                .get();
        System.out.println(response.getResult() + ", id=" + response.getId()); //输出创建结果，成功返回 CREATED
        return "添加索引成功！ \r\n"+response;
    }
        //2.商品修改
        public String updateCategory(String id, String name) throws Exception {
        UpdateRequest request = new UpdateRequest("test_db", "product", id);
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("name", name)
                .endObject();
        request.doc(builder);
        UpdateResponse response = client.update(request).get();
        System.out.println(response); //输出修改结果，成功返回 UPDATED
        System.out.println(response.getResult()); //输出修改结果，成功返回 UPDATED
            return "修改索引成功！";
        }
        //3.商品删除
        public String deleteCategory(String id)throws Exception{
        DeleteResponse response = client.prepareDelete("test_db", "product",  id).get();
        System.out.println(response.getResult()); //输出删除结果，成功返回 DELETED
            return "删除索引成功！";
        }
        //4.根据ID查询商品信息
        //@GetMapping("")  查询方法--restful风格
//        @GetMapping("/get/{id}")
        public String getCategory(String id) { //@PathVariable 获取/get/{id}中id
            System.out.println("根据id查询："+id);
        GetResponse response = client.prepareGet("test_db", "product",  id).get();
        System.out.println(response.getSource()); //输出查询结果
            return "查询索引成功！\r\n" +response;
        }
        //5.分页查询
        public String pageCategory(int start,int size) throws Exception {
        SearchResponse response = client.prepareSearch("test_db")
                .setTypes("product")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setFrom(0)
                .setSize(10)
                .get();
        System.out.println(response); //输出复合查询结果
            SearchHit[] hits = response.getHits().getHits();
            for (SearchHit hit : hits) {
                System.out.println(hit);
            }
            return "查询索引成功！";
        }
}
