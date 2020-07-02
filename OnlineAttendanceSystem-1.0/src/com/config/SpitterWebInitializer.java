package com.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.h2.server.web.WebServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.web.WebConfig;

/**
 * 初始化配置，相当于一个程序的入口类，在整个WEB启动时加载
 * 
 *
 * 关于 AbstractAnnotationConfigDispatcherServletInitializer 在 Servlet 3.0
 * 环境下，Servlet 容器会在 classpath 下搜索实现了 javax.servlet.ServletContainerInitializer
 * 接口的任何类，找到之后用它来初始化 Servlet 容器。 Spring 实现了以上接口，实现类叫做
 * SpringServletContainerInitializer， 它会依次搜寻实现了
 * WebApplicationInitializer的任何类，并委派这个类实现配置。之后，Spring 3.2 开始引入一个简易的
 * WebApplicationInitializer 实现类，这就是
 * AbstractAnnotationConfigDispatcherServletInitializer。 所以
 * SpittrWebAppInitializer 继承
 * AbstractAnnotationConfigDispatcherServletInitializer之后，也就是间接实现了
 * WebApplicationInitializer，在 Servlet 3.0 容器中，它会被自动搜索到，被用来配置 servlet 上下文。
 * 
 * 
 * @author wben
 * @version v2.0
 */
public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 在 Spring web 程序中，还有另外一个应用程序上下文，它是由 ContextLoaderListener 产生的。通过调用
	 * getRootConfigClasses()方法返回的类就是用来配置 ContextLoaderListener 产生的上下文。
	 * ContextLoaderListener 用来载入程序中其余的 beans，例如一些中间层和数据层组件，完成的是程序后端功能。
	 * 
	 * 
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	/**
	 * DispatcherServlet 开始启动时，会产生一个 Spring 应用程序上下文，把它和配置文件中声明的 bean
	 * 或者类一起加载进来。通过getServletConfigClasses() 方法，设置 DispatcherServlet 通过
	 * WebConfig 配置类来完成 Spring 上下文和 bean 的加载。 DispatcherServlet 是用来加载涉及 web 功能的
	 * beans，例如 controllers， view， resolvers， 和 handler mappings；
	 * 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	/**
	 * 为 DispatcherServlet 提供一个或更多的Servlet 映射；这里是被映射到 /， 指示它为默认的
	 * servlet，用来操作所有来到程序的 Request 题外话 问： /和/* 有什么区别？ 答： /会拦截除了jsp以外的所有url，/*
	 * 会拦截所有url，包括jsp。 例如：在webroot下面有一个test.jsp,当DispatcherServlet 配置映射/ 时，
	 * 浏览器输入：http://localhost:8080/test.jsp 这个jsp是可以直接访问的，并且不经过DispatcherServlet
	 * ， 而当DispatcherServlet 配置映射/* 时，这个请求就会被DispatcherServlet 拦截。
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		// 启动h2数据库控制台，通过http://localhost:8080/ex5/console访问数据库管理工具
		// 使用数据库mysql,具体配置见DataConfig中的dataSource()

		ServletRegistration.Dynamic servlet = servletContext.addServlet("mysql", new WebServlet());
		servlet.setLoadOnStartup(2);
		servlet.addMapping("/console/*");
	}

}