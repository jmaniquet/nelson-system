package org.nelson.system.core.web.theme;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "themeConverter")
public class ThemeConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		Theme of = Theme.of(value);
		return of;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		Theme asTheme = (Theme) object;
		if(object != null) {
			return asTheme.getThemeName();
		}
		
		return Theme.defaultTheme().getThemeName();
	}

}
