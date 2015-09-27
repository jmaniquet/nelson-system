package org.nelson.system.core.api;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.nelson.system.core.api.mybatis.mapper.CoreMapper;
import org.nelson.system.core.api.spring.jdbc.template.JodaTimeJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

@Configuration
@EnableTransactionManagement
@MapperScan(
		basePackages = {"org.nelson.system"},
		markerInterface=CoreMapper.class,
		sqlSessionFactoryRef="sqlSessionFactory")
@ComponentScan(basePackageClasses = CoreApiScannable.class)
public class CoreApiConfig {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcTemplate customJdbcTemplate() {
		return new JodaTimeJdbcTemplate(dataSource);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeHandlersPackage("org.nelson.system.core.api.mybatis.handler");
		// TODO : vérifier pourquoi ce n'est plus nécessaire
		 /*Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:org/nelson/system/*Mapper.xml");
		 sqlSessionFactoryBean.setMapperLocations(resources);*/
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public ResourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
