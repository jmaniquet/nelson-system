package org.nelson.system.core.web.calendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.primefaces.component.calendar.Calendar;
import org.springframework.util.StringUtils;

@FacesConverter("dateTimeConverter")
public class DateTimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (!StringUtils.hasText(value)) {
			return null;
		}
		Calendar cal = castToCalendar(component);
		DateTimeFormatter dtf = calculateDateTimeFormatter(cal);
		
		return DateTime.parse(value, dtf);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		
		Calendar cal = castToCalendar(component);
		DateTimeFormatter dtf = calculateDateTimeFormatter(cal);
		
		DateTime date = (DateTime) value;
		String result = date.toString(dtf);
		
		return result;
	}
	
	private DateTimeFormatter calculateDateTimeFormatter(Calendar cal) {
		String pattern = cal.calculatePattern();
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		return dtf;
	}

	private Calendar castToCalendar(UIComponent component) {
		if (!(component instanceof Calendar)) {
			throw new ConverterException(new FacesMessage("Le dateTimeConverter ne fonctionne que pour les primefaces Calendar"));
		}
		
		Calendar cal = (Calendar) component;
		return cal;
	}
}
