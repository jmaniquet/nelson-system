package org.nelson.system.core.api.context;

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

@Configuration
@MapperScan(
		basePackages = {"org.nelson.system"},
		markerInterface=CoreMapper.class,
		sqlSessionFactoryRef="sqlSessionFactory")
@ComponentScan(basePackages = "org.nelson.system.core.api")
public class CoreApiContext {

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
}
