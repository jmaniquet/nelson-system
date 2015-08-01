package org.nelson.system.tools.test.core.context;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
@ComponentScan("org.nelson.system.tools.test.core")
public class ToolsTestCoreContext {
	
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder dsb = new EmbeddedDatabaseBuilder()
				.setType(HSQL)
				.addScript("classpath:schema/nelson-schema.sql")
				.setSeparator("/;");
		return dsb.build();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
