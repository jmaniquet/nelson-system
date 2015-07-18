package org.nelson.system.core.api.spring.jdbc.template;

import java.util.Map;

import javax.sql.DataSource;

import org.nelson.system.core.api.spring.jdbc.argsetter.JodaTimeArgumentPreparedStatementSetter;
import org.nelson.system.core.api.spring.jdbc.argsetter.JodaTimeArgumentTypePreparedStatementSetter;
import org.nelson.system.core.api.spring.jdbc.rowmapper.JodaTimeColumnMapRowMapper;
import org.nelson.system.core.api.spring.jdbc.rowmapper.JodaTimeSingleColumnRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class JodaTimeJdbcTemplate extends JdbcTemplate {
	
	public JodaTimeJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	protected RowMapper<Map<String, Object>> getColumnMapRowMapper() {
		return new JodaTimeColumnMapRowMapper();
	}
	
	@Override
	protected <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requiredType) {
		return new JodaTimeSingleColumnRowMapper<T>(requiredType);
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
