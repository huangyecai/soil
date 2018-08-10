package com.yecai.garden.soil.modules.system.user.dao;

import com.yecai.garden.soil.modules.system.user.entity.User;
import com.yecai.garden.soil.system.common.annotation.MyBatisDao;

/**
 * 用户Dao
 * @author yecai
 * @date 2018-01-02
 */
@MyBatisDao
public interface UserDao {
	/**
	 * 根据id获取用户登陆详情
	 * @param user
	 * @return
	 */
	public User get(User user);
	/**
	 * 根据id获取用户详情
	 * @param user
	 * @return
	 */
	public User getById(User user);
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	public Integer insert(User user);
	/**
	 * 新增信息
	 * @param user
	 * @return
	 */
	public Integer insertInfo(User user);
	/**
	 * 检查是否存在这个人
	 * @param mobile
	 * @return
	 */
	public User getByMobile(String mobile);
}
