package org.nelson.system.personne.api;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public final class PersonneDaoFindByCriteriaDataConstants {
	
	//public static final int NB_USERS = 3;
	
	public static final String NAME_CRITERIA = "userNameA";
	public static final String GIVENNAME_CRITERIA = "userGivenNameB";
	
	public static final DateTime BIRTHDATE_STARTCLAUSE = DateTime.now()
			.withDayOfMonth(4)
			.withMonthOfYear(DateTimeConstants.OCTOBER)
			.withYear(1982)
			.withHourOfDay(0)
			.withMinuteOfHour(0)
			.withSecondOfMinute(0)
			.withMillisOfSecond(0);
	
	public static final DateTime BIRTHDATE_ENDCLAUSE = DateTime.now()
			.withDayOfMonth(4)
			.withMonthOfYear(DateTimeConstants.OCTOBER)
			.withYear(1992)
			.withHourOfDay(0)
			.withMinuteOfHour(0)
			.withSecondOfMinute(0)
			.withMillisOfSecond(0);
	
	private PersonneDaoFindByCriteriaDataConstants() {}
}
