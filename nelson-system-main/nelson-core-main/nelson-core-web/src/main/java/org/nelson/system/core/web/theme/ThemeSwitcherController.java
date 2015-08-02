package org.nelson.system.core.web.theme;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.nelson.system.core.web.user.preferences.UserPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ThemeSwitcherController {
	
	private static final Logger logger = LoggerFactory.getLogger(ThemeSwitcherController.class);
	
	private List<Theme> themes;
	
	@Autowired
	private UserPreferences userPreferences;

	@PostConstruct
	public void init() {
		Set<Theme> themeSet = EnumSet.allOf(Theme.class);
		
		themes = new ArrayList<>();
		themes.addAll(themeSet);
	}
	
	public List<Theme> getThemes() {
		return themes;
	}
	
	public void saveTheme(/*Theme theme*/) {
		logger.info("Save theme - value = {}", userPreferences.getTheme());	
	}
}
