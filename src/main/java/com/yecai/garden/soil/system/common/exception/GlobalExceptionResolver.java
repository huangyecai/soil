package com.yecai.garden.soil.system.common.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ExceptionLogger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常捕获
 * 
 * @author yecai
 * @date 2018-01-02
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		Logger logger = LogManager.getLogger(handler.getClass());
		logger.error(handler, exception);
		return null;
	}

}
