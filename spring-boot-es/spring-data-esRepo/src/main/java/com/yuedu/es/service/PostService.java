package com.yuedu.es.service;

import com.yuedu.es.dao.PostRepository;
import com.yuedu.es.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhy
 * @create 2020-04-14-10:36
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public Object add() {
        Post post = new Post();
        post.setTitle("我是");
        post.setContent("我爱中华人民共和国");
        post.setWeight(1);
        post.setUserId(1);
        postRepository.save(post);
        post = new Post();
        post.setTitle("我是");
        post.setContent("中华共和国");
        post.setWeight(2);
        post.setUserId(2);
        return postRepository.save(post);
    }

}
