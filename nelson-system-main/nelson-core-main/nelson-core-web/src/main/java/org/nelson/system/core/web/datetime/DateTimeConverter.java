package org.nelson.system.core.web.datetime;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.nelson.system.core.web.datetime.dtfprovider.CalendarDateTimeFormatterProvider;
import org.nelson.system.core.web.datetime.dtfprovider.HtmlOutputTextDateTimeFormatterProvider;
import org.nelson.system.core.web.datetime.dtfprovider.JsfDateTimeFormatterProvider;
import org.springframework.util.StringUtils;

@FacesConverter(forClass = DateTime.class)
public class DateTimeConverter implements Converter {
	
	private List<JsfDateTimeFormatterProvider<? extends UIComponent>> dtfProviders;
	
	public DateTimeConverter() {
		dtfProviders = new ArrayList<>();
		dtfProviders.add(new CalendarDateTimeFormatterProvider());
		dtfProviders.add(new HtmlOutputTextDateTimeFormatterProvider());
	}
	
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
		for (JsfDateTimeFormatterProvider<? extends UIComponent> dtfProvider : dtfProviders) {
			if (dtfProvider.canHandle(component)) {
				return dtfProvider.calculateDateTimeFormatter(component);
			}
		}
		
		throw new ConverterException(new FacesMessage("Le dateTimeConverter ne supporte pas le type " + component.getClass()));
	}
}
