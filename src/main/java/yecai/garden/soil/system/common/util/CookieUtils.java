package yecai.garden.soil.system.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie辅助类
 * 
 * @author yecai
 * @date 2018-01-10
 */
public class CookieUtils {
	private final static String COOKIE_NAME = "user";

	/**
	 * 添加默认的user的cookie
	 * 
	 * @param value
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public static void addCookie(String value, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cookie cookie = new Cookie(COOKIE_NAME, java.net.URLEncoder.encode(value,"UTF-8"));
		cookie.setPath("/");
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
	}

	/**
	 * 添加自定义cookie
	 * 
	 * @param name名称
	 * @param value值
	 * @param MaxAge
	 * @param url
	 * @param request
	 * @param response
	 */
	public static void addCookie(String name, String value, int maxAge, String url, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(url);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 获取user的cookie
	 * 
	 * @param request
	 * @param response
	 * @return value
	 * @throws Exception 
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 如果没有 cookie，则返回 null。
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (COOKIE_NAME.equals(cookie.getName())) {
				return java.net.URLDecoder.decode(cookie.getValue(),"UTF-8");  
			}
		}
		return null;
	}

	/**
	 * 删除cookie
	 * 
	 * @param name
	 * @param request
	 * @param response
	 */
	public void deleteCookie(String name, HttpServletRequest request, HttpServletResponse response) {
		Cookie c = new Cookie(name, "");
		c.setMaxAge(0);
		//路径
		c.setPath(request.getContextPath());
		response.addCookie(c);
	}
}
