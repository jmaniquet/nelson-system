package org.nelson.system.core.web.datetime.dtfprovider;

import javax.faces.component.UIComponent;

import org.joda.time.format.DateTimeFormatter;

public abstract class AbstractJsfDateTimeFormatterProvider<T extends UIComponent> implements JsfDateTimeFormatterProvider<T> {

	private Class<T> clazz;
	
	protected AbstractJsfDateTimeFormatterProvider(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DateTimeFormatter calculateDateTimeFormatter(UIComponent component) {
		T casted = (T) component;
		return doCalculateDateTimeFormatter(casted);
	}
	
	@Override
	public boolean canHandle(UIComponent component) {
		return clazz.isInstance(component);
	}
	
	protected abstract DateTimeFormatter doCalculateDateTimeFormatter(T casted);
}
