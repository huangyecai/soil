package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import yecai.garden.soil.hr.user.entity.User;
import yecai.garden.soil.hr.user.service.UserService;

/**
 * 
 * @author yecai
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class UserTest {
	@Autowired
	private UserService userService;

	@Test
	public void test() {
//		User user = new User();
//		user.setId("2");
//		User list = userService.getInfoById(user);
//		System.out.println(list.getName());
		boolean bool= userService.checkUserByMobile("123");
		System.out.println(bool);
		
	}
}
