package com.sto.es.controller;

import com.sto.es.entities.Post;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhy
 * @create 2020-04-10-18:06
 */
@RestController
public class ElasticsearchDemoController {

    @Resource(name = "elasticsearchTemplate")
    private ElasticsearchTemplate elasticsearchTemplate;

    private static final Logger logger= LoggerFactory.getLogger(ElasticsearchDemoController.class);

    /***
     * 插入一条数据
     */
    @GetMapping("/saveOne")
    public void  saveOne(){
        Post car  = new Post();
        car.setId("10000");
        car.setContent("您好 跑死他们");
        car.setTitle("hahahahahhha");
        car.setUserId(1001);
        //可以根据其他数据进行更新
        IndexQuery indexQuery =   new IndexQueryBuilder().withId(car.getId()).withObject(car).build();
        elasticsearchTemplate.index(indexQuery);
    }

    /**
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     */
    @RequestMapping("/singleWord")
    public Object singleTitle(String word, @PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(word)).withPageable(pageable).build();


        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }


    /**
     * 单字符串模糊查询，单字段排序。
     */
    @RequestMapping("/singleWord1")
    public Object singlePost(String word, @PageableDefault(sort = "weight", direction = Sort.Direction.DESC) Pageable pageable) {
        //使用QueryBuilders.queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(word)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }


    /**
     * 单字段对某字符串模糊查询
     */
    @RequestMapping("/singleMatch")
    public Object singleMatch(String content, Integer userId, @PageableDefault Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("content", content)).withPageable(pageable).build();
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("userId", userId)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }


    /**
     * 单字段对某短语进行匹配查询，短语分词的顺序会影响结果
     */
    @RequestMapping("/singlePhraseMatch")
    public Object singlePhraseMatch(String content, @PageableDefault Pageable pageable) {
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("content", content)).withPageable(pageable).build();
        //少匹配一个分词也OK、
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("content", content).slop(2)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    /*==========================================================================*/
    //1.商品添加
    //@PutMapping("add") 添加方法--restful风格
//    @GetMapping("add")
    //@RequestParam 接收页面中的请求的参数
//    public String addCategory(@RequestParam String name) throws IOException {
//        XContentBuilder builder = XContentFactory.jsonBuilder()
//                .startObject()
//                .field("id", 11)
//                .field("name", name)
//                .endObject();
//        IndexResponse response = client.prepareIndex("test_db", "product")//修改自己对应的“索引库”和“表”：test_db/product
//                .setSource(builder)
//                .get();
//        System.out.println(response.getResult() + ", id=" + response.getId()); //输出创建结果，成功返回 CREATED
//        return "添加索引成功！ \r\n"+response;
//    }
    //2.商品修改
    @GetMapping("update/{id}/{name}")
    //@RequestBody 接收页面中的请求的参数对象（适用于post请求）
    //当入参为实体对象时，需要在方法上加@Valid或@Validated或者在参数前加@Valid或@Validated，或者在类上加@Validated
    public String updateCategory(@PathVariable("id") String id, @PathVariable("name") String name) throws Exception {
//        UpdateRequest request    = new UpdateRequest("test_db", "product", id);//int 转String
//        XContentBuilder builder = XContentFactory.jsonBuilder()
//                .startObject()
//                .field("name", name)
//                .endObject();
//        request.doc(builder);
//        UpdateResponse response = client.update(request).get();
//        System.out.println(response.getResult()); //输出修改结果，成功返回 UPDATED
        return "修改索引成功！";
    }
    //3.商品删除
    //@DeleteMapping("/delete/{id}") 删除方法--restful风格
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String id)throws Exception{  //@PathVariable 获取/delete/{id}中id
//        DeleteResponse response = client.prepareDelete("test_db", "product",  id).get();
//        System.out.println(response.getResult()); //输出删除结果，成功返回 DELETED
        return "删除索引成功！";
    }
    //4.根据ID查询商品信息
    //@GetMapping("")  查询方法--restful风格
    @GetMapping("/get/{id}")
    public String getCategory(@PathVariable String id) { //@PathVariable 获取/get/{id}中id
        System.out.println("根据id查询："+id);
//        GetResponse response = client.prepareGet("test_db", "product",  id).get();
//        System.out.println(response.getSource()); //输出查询结果
        return "查询索引成功！";
    }
    //5.分页查询
    //@GetMapping("")  查询方法--restful风格
    @GetMapping("/page")
    public String pageCategory(@RequestParam(value="start",defaultValue="0")int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
//        SearchResponse response = client.prepareSearch("test_db")
//                .setTypes("product")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setFrom(0)
//                .setSize(10)
//                .get();
//        System.out.println(response); //输出复合查询结果
        return "查询索引成功！";
    }
}
