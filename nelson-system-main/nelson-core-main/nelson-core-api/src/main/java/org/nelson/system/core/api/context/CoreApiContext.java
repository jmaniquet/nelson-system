package org.nelson.system.core.api.context;

import javax.sql.DataSource;

import org.nelson.system.core.api.spring.jdbc.template.JodaTimeJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = "org.nelson.system.core.api")
public class CoreApiContext {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcTemplate customJdbcTemplate() {
		return new JodaTimeJdbcTemplate(dataSource);
	}
}
