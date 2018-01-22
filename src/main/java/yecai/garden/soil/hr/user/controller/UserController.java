package yecai.garden.soil.hr.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yecai.garden.soil.hr.user.entity.User;
import yecai.garden.soil.hr.user.service.UserService;
import yecai.garden.soil.system.common.tool.CookieTool;
import yecai.garden.soil.system.common.tool.HttpReturnTool;

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
	 * @throws Exception 
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	@ResponseBody
	public  Map<String, Object> userInser(User user, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map =new HashMap<String, Object>();
		int result=userService.register(user);
		return  HttpReturnTool.returnMap(result);
	}
	/**
	 * 通过id获取user信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "userinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUser(User user, HttpServletRequest request, HttpServletResponse response) {
		User user1=userService.getInfoById(user);
		return  HttpReturnTool.returnMap("200","success","user",user1);
	}

	/**
	 * 登录
	 * 
	 * @param account(账号)
	 * @param password(密码)
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> login(String account, String password, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		User user=null;
		if (account != null && !account.equals("") && password != null && !password.equals("")) {
			//数据验证
			if (userService.checkAccount(account, password)) {
				user=userService.getByMobile(account);
				//添加cookie
				CookieTool.addCookie(account, request, response);
				CookieTool.addCookie("sysUserId", user.getId(), -1, "/", request, response);
				CookieTool.addCookie("sysUserName",java.net.URLEncoder.encode( user.getName(),"utf8"), -1, "/", request, response);
				return  HttpReturnTool.returnMap("200","success","user",user);
			}
		}
		return    HttpReturnTool.returnMap("201","fail","用户名或密码错误！");
	}
}
