package com.yuedu.es.dao;

import com.yuedu.es.entities.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Component;

/**
 * @author zhy
 * @create 2020-04-13-22:11
 */

@Component
public interface PostRepository extends ElasticsearchCrudRepository<Post, String> {

    Iterable<Post> findAll();
}
