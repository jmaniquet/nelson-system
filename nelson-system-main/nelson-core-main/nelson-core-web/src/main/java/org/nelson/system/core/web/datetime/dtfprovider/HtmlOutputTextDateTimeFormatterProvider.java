package org.nelson.system.core.web.datetime.dtfprovider;

import javax.faces.component.html.HtmlOutputText;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

public class HtmlOutputTextDateTimeFormatterProvider extends AbstractJsfDateTimeFormatterProvider<HtmlOutputText> {

	private static final String STD_DATE_PATTERN = "dd/MM/yyyy";
	
	public HtmlOutputTextDateTimeFormatterProvider() {
		super(HtmlOutputText.class);
	}
	
	@Override
	protected DateTimeFormatter doCalculateDateTimeFormatter(HtmlOutputText casted) {
		String pattern = (String) casted.getAttributes().get("pattern");
		pattern = StringUtils.hasText(pattern) ? pattern : STD_DATE_PATTERN;
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		return dtf;
	}
}
