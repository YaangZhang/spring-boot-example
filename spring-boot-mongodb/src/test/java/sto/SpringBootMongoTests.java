package sto;

import com.sto.SpringBootMongoApplication;
import com.sto.mongo.model.UserInfo;
import com.sto.mongo.model.repository.UserReporitory;
import com.sto.mongo.utils.DBDaoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMongoApplication.class)
public class SpringBootMongoTests {

	@Autowired
	private UserReporitory userRepository;

	@Autowired
    private DBDaoTest dbDaoTest;

	@Test
	public void dbtest(){
        List<UserInfo> all = dbDaoTest.findAll(UserInfo.class, "USER_INFO");
        System.out.println(all);

        Query query = new Query(Criteria.where("userName").is("111234"));
        UserInfo user_info = dbDaoTest.findOne(UserInfo.class, query, "USER_INFO");
        System.out.println(user_info);
        UserInfo byUserName = userRepository.findByUserName("111234", "USER_INFO");
		System.out.println(byUserName);
	}
@Test
	public void test(){
		UserInfo byUserName = userRepository.findByUserName("123344", "USER_INFO");
		System.out.println(byUserName);
	}

	@Test
	public void testAll(){
        List<UserInfo> all = userRepository.findAll();
        System.out.println(all);
	}

	@Test
	public void pageAll(){
		Pageable pageRequest = PageRequest.of(0, 10, new Sort(Sort.Direction.DESC, "createTime"));

		Page<UserInfo> infoPage = userRepository.findAll(pageRequest.first());
		System.out.println(infoPage);
	}
	@Test
	public void contextLoads() {
	}

}
