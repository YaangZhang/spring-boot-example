package com.sto.es;

import com.sto.es.service.EsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author zhy
 * @create 2020-04-14-11:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EsServiceTest {

    @Autowired
    private EsService esService;

    @Test
    public void testAdd(){
        String adny = null;
        try {
            adny = esService.addCategory("adny");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(adny);
    }

     @Test
    public void testSearch(){
        String adny = esService.getCategory("wWQkd3EBaILZD6mkhChT");
        System.out.println(adny);
    }

    @Test
    public void testUpdate(){
        String adny = null;
        try {
            adny = esService.updateCategory("wWQkd3EBaILZD6mkhChT", "java 入门");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(adny);
    }

    @Test
    public void testPage(){
        String adny = null;
        try {
            adny = esService.pageCategory(0,10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(adny);
    }



}
