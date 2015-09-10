package org.nelson.system.core.web.calendar;

import javax.faces.convert.Converter;

import org.primefaces.component.calendar.Calendar;

/**
 * Work around for primefaces not correctly using a converter registered with a "forClass" attribute on the Calendar component.<br/>
 * The problem lies in the CalendarUtils class : the method getValueAsString checks for a specific converter on the component,<br/>
 * but if not found defaults on a java.util.Date conversion with SimpleDateFormat.
 * Converters registered with "forClass" are thus ignored.<br/>
 * More details at :<br/>
 * http://stackoverflow.com/questions/31598697/facesconverterforclass-clazz-class-and-pcalendar
 * @author jonathan
 */
public class DateTimeCalendar extends Calendar {

	@Override
	public Converter getConverter() {
		Converter converter = super.getConverter();
		if(converter == null){
			return new DateTimeConverter();
		}
		return converter;
	}
}
