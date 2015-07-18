package org.nelson.system.core.api.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;

@MappedTypes(value = DateTime.class)
public class JodaTimeTypeHandler implements TypeHandler<DateTime> {

	@Override
	public void setParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setNull(i, Types.TIMESTAMP);
		} else {
			ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
		}
	}

	@Override
	public DateTime getResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp ts = rs.getTimestamp(columnName);
		if (rs.wasNull() || ts == null) {
			return null;
		}
		return new DateTime(ts.getTime());
	}

	@Override
	public DateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp ts = rs.getTimestamp(columnIndex);
		if (rs.wasNull() || ts == null) {
			return null;
		}
		return new DateTime(ts.getTime());
	}

	@Override
	public DateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp ts = cs.getTimestamp(columnIndex);
		if (cs.wasNull() || ts == null) {
			return null;
		}
		return new DateTime(ts.getTime());
	}

}
