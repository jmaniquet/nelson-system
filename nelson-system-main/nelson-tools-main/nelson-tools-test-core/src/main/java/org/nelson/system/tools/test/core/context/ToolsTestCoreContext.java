package org.nelson.system.tools.test.core.context;

import javax.sql.DataSource;

import org.nelson.system.infrastructure.environment.InfrastructureEnvironmentConfig;
import org.nelson.system.tools.test.core.ToolsTestCoreScannable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Import(InfrastructureEnvironmentConfig.class)
@ComponentScan(basePackageClasses = ToolsTestCoreScannable.class)
public class ToolsTestCoreContext {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
}
