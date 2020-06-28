package com.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.domain.Member;
import com.domain.Manager;

/**
 * 利用拦截器对访问路径进行登录校验
 * 
 * @author qiuli
 * @version 1.0
 *
 */
public class ManagerInterceptor implements HandlerInterceptor {

	private final static Log log = LogFactory.getLog(ManagerInterceptor.class);

	/**
	 * 
	 * afterCompletion方法会在一个请求完成之后被调用 。如渲染视图之后。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception exception) throws Exception {
		log.debug("3.Called after rendering the view");

	}

	/**
	 * 
	 * postHandle方法会在controller类的处理方法调用之后， 但是在渲染视图之前运行。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView model)
			throws Exception {
		log.debug("2.Called after handler method request completion, before rendering the view");

	}

	/**
	 * 
	 * preHandle会在一个控制器类的处理方法被调用之前执行此方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		log.debug("1.Called before handler method");
		// 获取session
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		Manager manager = (Manager) session.getAttribute("manager");

		// 判断session中是否有用户数据，如果有，则返回true，继续向下执行
		if (manager != null) {
			return true;
		} else if (member != null) {
			response.sendRedirect(request.getContextPath() + "/member");
			return false;
		}
		// 不符合条件的转发到登录页面
		response.sendRedirect(request.getContextPath() + "/home");
		return false;
	}

}
