package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yecai.garden.soil.modules.system.user.entity.User;
import com.yecai.garden.soil.modules.system.user.service.UserService;

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
	public void test() throws Exception {
//		User user=new User();
//		user.setMobile("123");
//		user.setPassword("12");
//		user.setFamilyName("黄");
//		user.setFirstName("野菜");
////		int result=userService.register(user);
//		boolean result=userService.checkAccount(user.getMobile(), user.getPassword());
//		System.out.println(result);
//		User user=userService.getByMobile("123");
//		System.out.println(user.getMobile());
	   
	}
}
