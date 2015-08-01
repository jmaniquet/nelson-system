package org.nelson.system.core.web.user.preferences;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.nelson.system.core.web.theme.Theme;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserPreferences implements Serializable {

	private static final long serialVersionUID = -4820850295006752574L;
	
	private Theme theme;

	@PostConstruct
	public void init() {
		theme = Theme.defaultTheme(); // default value
	}
	
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}
