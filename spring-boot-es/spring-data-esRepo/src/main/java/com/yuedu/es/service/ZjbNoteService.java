package com.yuedu.es.service;

import com.yuedu.es.dao.ZjbNoteEsRepository;
import com.yuedu.es.entities.EsZjbNote;
import com.yuedu.es.entities.Post;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author zhy
 * @create 2020-04-09-15:09
 */
@Service("zjbNoteService")
public class ZjbNoteService {

    @Autowired
    private ZjbNoteEsRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /***
     * 插入一条数据
     */
    public void  saveOne(){
        Post car  = new Post();
        car.setId("10000");
        car.setContent("您好 跑死他们");
        car.setTitle("hahahahahhha");
        car.setUserId(1001);
        //可以根据其他数据进行更新
        IndexQuery indexQuery =   new IndexQueryBuilder().withId(car.getId()).withObject(car).withSource("car").build();
        String index = elasticsearchTemplate.index(indexQuery);
        System.out.println(index);
    }

    /**
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     */
    public Object singleTitle(String word, @PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(word)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }


    /**
     * 单字符串模糊查询，单字段排序。
     */
    public Object singlePost(String word, @PageableDefault(sort = "weight", direction = Sort.Direction.DESC) Pageable pageable) {
        //使用QueryBuilders.queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(word)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

/*===========================================*/

    public EsZjbNote findById(String id){
        Optional<EsZjbNote> byId = repository.findById("023dc4f1a0c9451ab6e88962f9b623ea");
        EsZjbNote note = byId.get();
        System.out.println("查询结果 == "+note);
return note;
    }

    public EsZjbNote save(){
        EsZjbNote note = new EsZjbNote();
        note.setF_note_id("023dc4f1a0c9451ab6e88962f9b623ea");
        note.setF_user_id("666666");
        note.setF_content("费哥好帅啊");
        note.setF_keyword("keyword");
        note.setF_local("中国山东济南历下区");
        //仓库接口
        EsZjbNote save = repository.save(note);
        System.out.println("插入结果 == "+save);
        return save;
    }
}
