package org.nelson.system.core.web.context;

import org.nelson.system.core.api.basenames.MessageLocationProvider;
import org.nelson.system.core.api.basenames.MessageLocationProviderDefaultImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.nelson.system.core.web")
public class CoreWebContext {
	
	@Bean
	public MessageLocationProvider messageLocationProvider() {
		return new MessageLocationProviderDefaultImpl(
				"classpath:messages/template/default-layout",
				"classpath:messages/template/header",
				"classpath:messages/template/footer");
	}
	
	/*@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(
				"classpath:messages/template/default-layout",
				"classpath:messages/template/header",
				"classpath:messages/template/footer");
		return messageSource;
	}*/
}
