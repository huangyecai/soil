package yecai.garden.soil.hr.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yecai.garden.soil.hr.user.entity.User;
import yecai.garden.soil.hr.user.service.UserService;

/**
 * 用户Controller
 * 
 * @author yecai
 * @date 2018-01-02
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 用户新增
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	@ResponseBody
	public  Map<String, Object> userInser(User user, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map =new HashMap<String, Object>();
		int result=userService.register(user);
		String code = "200";
		String message = "success";
		String resultStr = "";
		if (result==1) {
			code = "200";
			message = "success";
			resultStr = "";
		}else
		{
			message = "fail";
			code = "201";
			resultStr = "用户名或密码错误！";
		}		 
		map.put("status", code);
		map.put("message", message);
		map.put("result", resultStr);
		return map;
	}
	/**
	 * 通过id获取user信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "userinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getuser(User user, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		User user1=userService.getInfoById(user);
		map.put("user", user1);
		return map;
	}

	/**
	 * 登录
	 * 
	 * @param account(账号)
	 * @param password(密码)
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> login(String account, String password, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String message = "success";
		String result = "";
		if (account != null && !account.equals("") && password != null && !password.equals("")) {
			//数据验证
			
		} else {
			message = "fail";
			code = "201";
			result = "用户名或密码错误！";
		}
		map.put("status", code);
		map.put("message", message);
		map.put("result", result);
		return map;
	}
}
