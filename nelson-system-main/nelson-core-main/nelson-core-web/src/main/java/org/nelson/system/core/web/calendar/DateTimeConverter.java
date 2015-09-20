package org.nelson.system.core.web.calendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.primefaces.component.calendar.Calendar;
import org.springframework.util.StringUtils;

@FacesConverter(forClass = DateTime.class)
public class DateTimeConverter implements Converter {

	private static final String STD_DATE_PATTERN = "dd/MM/yyyy";
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (!StringUtils.hasText(value)) {
			return null;
		}
		
		DateTimeFormatter dtf = calculateDateTimeFormatter(component);
		return DateTime.parse(value, dtf);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		
		DateTimeFormatter dtf = calculateDateTimeFormatter(component);
		
		DateTime date = (DateTime) value;
		String result = date.toString(dtf);
		
		return result;
	}
	
	private DateTimeFormatter calculateDateTimeFormatter(UIComponent component) {
		if (isInstanceOfCalendar(component)) {
			Calendar casted = castToCalendar(component);
			return calculateDateTimeFormatter(casted);
		}
		
		if (isInstanceOfOutputText(component)) {
			HtmlOutputText casted = castToOutputText(component);
			return calculateDateTimeFormatter(casted);
		}
		
		throw new ConverterException(new FacesMessage("Le dateTimeConverter ne fonctionne que pour les primefaces Calendar et les jsf OutputText"));
	}
	
	private DateTimeFormatter calculateDateTimeFormatter(Calendar cal) {
		String pattern = cal.calculatePattern();
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		return dtf;
	}
	
	private DateTimeFormatter calculateDateTimeFormatter(HtmlOutputText txt) {
		String pattern = (String) txt.getAttributes().get("pattern");
		pattern = StringUtils.hasText(pattern) ? pattern : STD_DATE_PATTERN;
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		return dtf;
	}

	private Calendar castToCalendar(UIComponent component) {		
		Calendar casted = (Calendar) component;
		return casted;
	}
	
	private HtmlOutputText castToOutputText(UIComponent component) {
		HtmlOutputText casted = (HtmlOutputText) component;
		return casted;
	}

	private boolean isInstanceOfCalendar(UIComponent component) {
		return component instanceof Calendar;
	}
	
	private boolean isInstanceOfOutputText(UIComponent component) {
		return component instanceof HtmlOutputText;	
	}
}
