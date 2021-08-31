package com.leichu.cas.config;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

	@Bean(name = "dataSource")
	public DataSource dataSource() throws Exception {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(MysqlProperty.url);
		dataSource.setUsername(MysqlProperty.username);
		dataSource.setPassword(MysqlProperty.password);
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setFilters(MysqlProperty.filters);
		dataSource.setMaxActive(Integer.parseInt(MysqlProperty.maxActive));
		dataSource.setInitialSize(Integer.parseInt(MysqlProperty.initialSize));
		dataSource.setMinIdle(Integer.parseInt(MysqlProperty.minIdle));

		dataSource.setMaxWait(Long.parseLong(MysqlProperty.maxWait));
		dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(MysqlProperty.timeBetweenEvictionRunsMillis));
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(MysqlProperty.minEvictableIdleTimeMillis));

		dataSource.setTestWhileIdle(Boolean.getBoolean(MysqlProperty.testWhileIdle));
		dataSource.setTestOnBorrow(Boolean.getBoolean(MysqlProperty.testOnBorrow));
		dataSource.setTestOnReturn(Boolean.getBoolean(MysqlProperty.testOnReturn));

		dataSource.setPoolPreparedStatements(Boolean.getBoolean(MysqlProperty.poolPreparedStatements));
		dataSource.setMaxOpenPreparedStatements(Integer.parseInt(MysqlProperty.maxOpenPreparedStatements));
		dataSource.setAsyncInit(Boolean.getBoolean(MysqlProperty.asyncInit));

		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}


	@Data
	static class MysqlProperty {
		public static final String url = "jdbc:mysql://10.8.0.246:3306/ei_rpt_kitty?useUnicode=true&characterEncoding=utf-8";
		public static final String username = "eiduo";
		public static final String password = "edGdas*h)";
		public static final String filters = "stat";

		public static final String maxActive = "50";
		public static final String initialSize = "5";
		public static final String minIdle = "2";

		public static final String maxWait = "60000";
		public static final String timeBetweenEvictionRunsMillis = "60000";
		public static final String minEvictableIdleTimeMillis = "300000";

		public static final String testWhileIdle = "true";
		public static final String testOnBorrow = "false";
		public static final String testOnReturn = "false";

		public static final String poolPreparedStatements = "true";
		public static final String maxOpenPreparedStatements = "20";
		public static final String asyncInit = "true";
	}

}
