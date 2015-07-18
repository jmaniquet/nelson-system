package org.nelson.system.core.api.spring.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class JodaTimeSingleColumnRowMapper<T> extends SingleColumnRowMapper<T> {

	public JodaTimeSingleColumnRowMapper(Class<T> requiredType) {
		super(requiredType);
	}
	
	@Override
	protected Object getColumnValue(ResultSet rs, int index, Class<?> requiredType) throws SQLException {
		if (requiredType == null || !DateTime.class.isAssignableFrom(requiredType)) {
			return super.getColumnValue(rs, index, requiredType);	
		}
		Date utilDate = (Date) JdbcUtils.getResultSetValue(rs, index, Date.class);
		return (utilDate != null ? new DateTime(utilDate.getTime()) : null);
	}
}
