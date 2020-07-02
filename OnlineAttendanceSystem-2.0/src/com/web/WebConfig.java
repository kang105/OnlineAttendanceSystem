package com.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * webMVC配置
 * 
 * @author qiuli
 * @version v1.0
 */
@Configuration
/*
 * 将@EnableWebMvc添加给@Configuration类来导入SpringMvc的配置；3.自定义MVC配置，
 * 实现接口WebMvcConfigurer或更可能继承WebMvcConfigurerAdapter,并且使用@EnableWebMvc;
 * 
 */
@EnableWebMvc
/*
 * 定义扫描的路径从中找出标识了需要装配的类自动装配到spring的bean容器中
 * 
 */
@ComponentScan("com.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 自定义视图解析器
	 * 
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * 注册一个默认的Handler：DefaultServletHttpRequestHandler，这个Handler也是用来处理静态文件的，
	 * 它会尝试映射/*。当DispatcherServelt映射/时（/ 和/** 是有区别的），
	 * 并且没有找到合适的Handler来处理请求时，就会交给DefaultServletHttpRequestHandler来处理。
	 * 注意：这里的静态资源是放置在web根目录下，而非WEB-INF 下。
	 * 可能这里的描述有点不好懂，所以简单举个例子。例如：在webroot目录下有一个图片：1.png
	 * 我们知道Servelt规范中web根目录（webroot）下的文件可以直接访问的，但是由于DispatcherServlet配置了映射路径是：/
	 * ，它几乎把所有的请求都拦截了，从而导致1.png 访问不到，这时注册一个DefaultServletHttpRequestHandler
	 * 就可以解决这个问题。其实可以理解为DispatcherServlet破坏了Servlet的一个特性（根目录下的文件可以直接访问），
	 * DefaultServletHttpRequestHandler是帮助回归这个特性的。
	 * 
	 * 题外话 问： /和/* 有什么区别？ 答： /会拦截除了jsp以外的所有url，/* 会拦截所有url，包括jsp。
	 * 例如：在webroot下面有一个test.jsp,当DispatcherServlet 配置映射/ 时，
	 * 浏览器输入：http://localhost:8080/test.jsp 这个jsp是可以直接访问的，并且不经过DispatcherServlet
	 * ， 而当DispatcherServlet 配置映射/* 时，这个请求就会被DispatcherServlet 拦截。
	 * 
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 定义静态资源处理
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		super.addResourceHandlers(registry);
	}

	/**
	 * 定义拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new MemberInterceptor()).addPathPatterns(new String[] { "/member", "/member/**" })// 添加拦截
				.excludePathPatterns("/home/**", "/home", "/manager", "/manager/**");// excludePathPatterns
																						// 排除拦截
		super.addInterceptors(registry);

		registry.addInterceptor(new ManagerInterceptor()).addPathPatterns(new String[] { "/manager", "/manager/**" })// 添加拦截
				.excludePathPatterns("/member", "/member/**", "/home/**", "/home");// excludePathPatterns
																					// 排除拦截
		super.addInterceptors(registry);
	}

}
