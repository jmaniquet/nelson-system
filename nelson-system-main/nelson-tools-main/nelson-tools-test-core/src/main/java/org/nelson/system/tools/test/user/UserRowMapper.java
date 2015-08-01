package org.nelson.system.tools.test.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
class UserRowMapper implements RowMapper<User> {

	private static final String BIRTH_DATE = "BIRTH_DATE";
	private static final String GIVEN_NAME = "GIVEN_NAME";
	private static final String ID = "ID";
	private static final String NAME = "NAME";
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		long rId = rs.getLong(ID);
		String rName = rs.getString(NAME);
		String rGivenName = rs.getString(GIVEN_NAME);
		Timestamp birthDateTs = rs.getTimestamp(BIRTH_DATE);
		DateTime birthDate = (rs.wasNull() ? null : new DateTime(birthDateTs.getTime()));
		
		return UserBuilder.builder()
				.id(rId)
				.name(rName)
				.givenName(rGivenName)
				.birthDate(birthDate)
				.build();
	}
}