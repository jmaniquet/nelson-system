package org.nelson.system.core.web.theme;

import java.io.Serializable;

public enum Theme implements Serializable {
	
	AFTERDARK("afterdark", "Afterdark"),
	AFTERNOON("afternoon", "Afternoon"),
	AFTERWORK("afterwork", "Afterwork"),
	ARISTO("aristo", "Aristo"),
	BLACK_TIE("black-tie", "Black-Tie"),
	BLITZER("blitzer", "Blitzer"),
	BLUESKY("bluesky", "Bluesky"),
	BOOTSTRAP("bootstrap", "Bootstrap"),
	CASABLANCA("casablanca", "Casablanca"),
	CRUZE("cruze", "Cruze"),
	CUPERTINO("cupertino", "Cupertino"),
	DARK_HIVE("dark-hive", "Dark-Hive"),
	DELTA("delta", "Delta"),
	DOT_LUV("dot-luv", "Dot-Luv"),
	EGGPLANT("eggplant", "Eggplant"),
	EXCITE_BIKE("excite-bike", "Excite-Bike"),
	FLICK("flick", "Flick"),
	GLASS_X("glass-x", "Glass-X"),
	HOME("home", "Home"),
	HOT_SNEAKS("hot-sneaks", "Hot-Sneaks"),
	HUMANITY("humanity", "Humanity"),
	LE_FROG("le-frog", "Le-Frog"),
	MIDNIGHT("midnight", "Midnight"),
	MINT_CHOC("mint-choc", "Mint-Choc"),
	OVERCAST("overcast", "Overcast"),
	PEPPER_GRINDER("pepper-grinder", "Pepper-Grinder"),
	REDMOND("redmond", "Redmond"),
	ROCKET("rocket", "Rocket"),
	SAM("sam", "Sam"),
	SMOOTHNESS("smoothness", "Smoothness"),
	SOUTH_STREET("south-street", "South-street"),
	START("start", "Start"),
	SUNNY("sunny", "Sunny"),
	SWANKY_PURSE("swanky-purse", "Swanky-Purse"),
	TRONTASTIC("trontastic", "Trontastic"),
	UI_DARKNESS("ui-darkness", "UI-Darkness"),
	UI_LIGHTNESS("ui-lightness", "UI-Lightness"),
	VADER("vader", "Vader");
	
	private static final long serialVersionUID = 6740716319758365273L;

	private String themeName;
	private String displayName;
	
	private Theme(String name, String displayName) {
		this.themeName = name;
		this.displayName = displayName;
	}
	
	public String getThemeName() {
		return themeName;
	}
	public String getDisplayName() {
		return displayName;
	}
	
	public static Theme of(String themeStr) {
		for (Theme theme : Theme.values()) {
			if (theme.getThemeName().equals(themeStr)) {
				return theme;
			}
		}
		return defaultTheme();
	}
	
	public static Theme defaultTheme() {
		return AFTERNOON;
	}
}
