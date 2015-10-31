package org.nelson.system.infrastructure.environment;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
@ComponentScan
public class InfrastructureEnvironmentConfig {
	
	@Bean
	@Profile({EnvProfile.DEV, EnvProfile.TEST})
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder dsb = new EmbeddedDatabaseBuilder()
				.setType(HSQL)
				.addScript("classpath:schema/nelson-schema.sql")
				.setSeparator("/;");
		return dsb.build();
	}
}
