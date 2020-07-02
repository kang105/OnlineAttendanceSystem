package com.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import com.config.RootConfig.WebPackage;

/**
 *
 * 配置类，用于管理ContextLoadListener创建的上下文的bean
 * 
 * @author wben
 * @version v2.0
 */
// 定义为配置类
@Configuration
// 引入数据库配置类
@Import(DataConfig.class)
// 定义Spring 扫描的包名，采用自定义扫描类WebPackage
@ComponentScan(basePackages = { "com" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class RootConfig {

	/**
	 * 自定义扫描类，采用正则方式，只扫描web包下的类
	 *
	 */
	public static class WebPackage extends RegexPatternTypeFilter {

		public WebPackage() {
			super(Pattern.compile("web"));
		}
	}
}
