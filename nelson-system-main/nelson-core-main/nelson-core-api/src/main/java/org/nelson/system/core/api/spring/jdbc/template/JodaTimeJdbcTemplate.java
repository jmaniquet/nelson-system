package org.nelson.system.core.api.spring.jdbc.template;

import javax.sql.DataSource;

import org.nelson.system.core.api.spring.jdbc.argsetter.JodaTimeArgumentPreparedStatementSetter;
import org.nelson.system.core.api.spring.jdbc.argsetter.JodaTimeArgumentTypePreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class JodaTimeJdbcTemplate extends JdbcTemplate {
	
	public JodaTimeJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	@Override
	protected PreparedStatementSetter newArgPreparedStatementSetter(Object[] args) {
		return new JodaTimeArgumentPreparedStatementSetter(args);
	}
	
	@Override
	protected PreparedStatementSetter newArgTypePreparedStatementSetter(Object[] args, int[] argTypes) {
		return new JodaTimeArgumentTypePreparedStatementSetter(args, argTypes);
	}
}
