package org.nelson.system.core.web.datetime.dtfprovider;

import javax.faces.component.UIComponent;

import org.joda.time.format.DateTimeFormatter;

/**
 * Interface providing contract to compute DateTimeFormatter instance in jsf/DateTime conversion.<br/>
 * For each jsf component needing JodaTime integration, a implementation of this class should be needed.
 * @author jonathan
 *
 * @param <T>
 */
public interface JsfDateTimeFormatterProvider<T extends UIComponent> {

	DateTimeFormatter calculateDateTimeFormatter(UIComponent component);
	
	boolean canHandle(UIComponent component);
}
