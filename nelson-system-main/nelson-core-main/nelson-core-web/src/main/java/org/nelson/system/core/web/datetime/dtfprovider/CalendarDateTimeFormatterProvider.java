package org.nelson.system.core.web.datetime.dtfprovider;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.primefaces.component.calendar.Calendar;

public class CalendarDateTimeFormatterProvider extends AbstractJsfDateTimeFormatterProvider<Calendar> {

	public CalendarDateTimeFormatterProvider() {
		super(Calendar.class);
	}
	
	@Override
	protected DateTimeFormatter doCalculateDateTimeFormatter(Calendar casted) {
		String pattern = casted.calculatePattern();
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		return dtf;
	}
}
