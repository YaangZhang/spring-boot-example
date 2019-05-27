package com.sto;

import com.sto.model.User;
import com.sto.repository.test1.User1Repository;
import com.sto.repository.test2.User2Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMultiJpaApplicationTests {
    @Resource
    private User1Repository userTest1Repository;
    @Resource
    private User2Repository userTest2Repository;

    @Test
    public void testSave() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        System.out.println("format :"+format);

        userTest1Repository.save(new User("aa", "aa123456","aa@126.com", "aa",  format));
        userTest1Repository.save(new User("bb", "bb123456","bb@126.com", "bb",  format));
        userTest2Repository.save(new User("cc", "cc123456","cc@126.com", "cc",  format));
    }

    @Test
    public void contextLoads() {
    }

}
