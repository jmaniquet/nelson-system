package org.nelson.system.core.api.spring.jdbc.template;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JodaTimeJdbcTemplate extends JdbcTemplate {
	public JodaTimeJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
}
