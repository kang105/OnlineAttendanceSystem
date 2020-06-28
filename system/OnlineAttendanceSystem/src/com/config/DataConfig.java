package com.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * 数据库配置
 * 
 * @author liuShuai
 * @version v1.1
 */
@Configuration
public class DataConfig {

	/**
	 * 数据源设置，采用mysql数据库
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl(
				"jdbc:mysql://localhost:3306/OnlineAttendanceSystem?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&amp;");// v1.1
		ds.setUsername("root");
		ds.setPassword("123456");
		return ds;
	}

	/**
	 * jdbc模板配置，采用spring默认的JdbcTemplate模板
	 * 
	 * @param dataSource
	 *            数据源
	 * @return
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
