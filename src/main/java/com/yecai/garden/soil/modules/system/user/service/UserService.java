package com.yecai.garden.soil.modules.system.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yecai.garden.soil.modules.system.user.dao.UserDao;
import com.yecai.garden.soil.modules.system.user.entity.User;
import com.yecai.garden.soil.system.common.util.DataEncryptionStandardUtils;


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
		return userDao.getById(user);
	}
	/**
	 * 根据id获取登录详情
	 * @param user
	 * @return
	 */
	public User getById(User user){
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
	 * 通过手机号查用户
	 * @param user
	 * @return
	 */
	public User getByMobile(String mobile){
		return userDao.getByMobile(mobile);
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
	 * @throws Exception 
	 */
	public int register(User user) throws Exception {
		if (!checkUserByMobile(user.getMobile())) {
			//拼凑姓名
			user.setName(user.getFamilyName()+user.getFirstName());
			//加密密码
			String password= DataEncryptionStandardUtils.encrypt(user.getPassword());
			user.setPassword(password);
			user.preInsert();
			this.insert(user);
			return this.insertInfo(user);
		}
		return 0;
	}
	/**
	 * 校验该手机是否存在
	 * @param mobile
	 * @return
	 */
	public boolean checkUserByMobile(String mobile){		 
		if (this.getByMobile(mobile)!=null) {
			return true;
		}
		return false;
	}
	/**
	 * 密码校验
	 * @param password 密文
	 * @param userPassword 明文 
	 * @return
	 * @throws Exception 
	 */
	public boolean checkPassword(String password,String userPassword) throws Exception{		 
			 
			String pw=DataEncryptionStandardUtils.decrypt(password);
			if (pw.equals(userPassword)) {
				return true;
			}
			return false;		 
	}
	/**
	 * 验证账号和密码
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	public boolean checkAccount(String account,String password) throws Exception{
		if (checkUserByMobile(account)) {			 
			User user=this.getByMobile(account);
			user=this.getById(user);
			if (this.checkPassword(user.getPassword(), password)) {
				return true;
			}
			return false;
		}
		return false;
	}
}
