package com.sto;

import com.sto.domain.User;
import com.sto.domain.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootWebApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void test(){
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);

		// userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456"));
		// userRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456"));
		// userRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456"));

		Assert.assertEquals(9, userRepository.findAll().size());
		// Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@126.com").getNickName());
		// userRepository.delete(userRepository.findByUserName("bb"));
	}

	@Test
	public void contextLoads() {
	}

}
