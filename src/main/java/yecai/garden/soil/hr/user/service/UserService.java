package yecai.garden.soil.hr.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yecai.garden.soil.hr.user.dao.UserDao;
import yecai.garden.soil.hr.user.entity.User;

/**
 * 用户Service
 * 
 * @author yecai
 * @date 2018-01-02
 */
@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	/**
	 * 根据id查用户
	 * 
	 * @param user
	 * @return
	 */
	public User getInfoById(User user) {
		return userDao.get(user);
	}

	/**
	 * 新增用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int insertInfo(User user) {
		if (user.getId() == null || user.getId().equals("")) {
			user.preInsert();
		}
		return userDao.insertInfo(user);
	}

	/**
	 * 新增登录信息
	 * 
	 * @param user
	 * @return
	 */
	public int insert(User user) {
		if (user.getId() == null || user.getId().equals("")) {
			user.preInsert();
		}
		return userDao.insert(user);
	}

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	public int register(User user) {
		user.preInsert();
		this.insert(user);
		return this.insertInfo(user);
	}
	/**
	 * 校验该手机是否存在
	 * @param mobile
	 * @return
	 */
	public boolean checkUserByMobile(String mobile){
		int result=userDao.checkByMobile(mobile);
		System.out.println(result);
		if (result>0) {
			return true;
		}
		return false;
	}
}
