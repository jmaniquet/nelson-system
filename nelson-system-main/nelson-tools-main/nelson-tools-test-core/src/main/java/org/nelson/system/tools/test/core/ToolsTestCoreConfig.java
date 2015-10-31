package org.nelson.system.tools.test.core;

import javax.sql.DataSource;

import org.nelson.system.infrastructure.environment.InfrastructureEnvironmentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Import(InfrastructureEnvironmentConfig.class)
@ComponentScan
public class ToolsTestCoreConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
}
