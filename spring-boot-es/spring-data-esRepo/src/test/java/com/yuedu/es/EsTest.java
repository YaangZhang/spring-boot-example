package com.yuedu.es;

import com.yuedu.es.service.EsService;
import com.yuedu.es.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhy
 * @create 2020-04-16-14:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com.yuedu.es/applicationContext.xml")
public class EsTest {

    @Autowired
    PostService postService;

    @Autowired
    EsService esService;
    @Test
    public void test1(){
        Object add = postService.add();
    }
}
