package org.nelson.system.personne.web.context;

import org.nelson.system.core.api.basenames.MessageLocationProvider;
import org.nelson.system.core.api.basenames.MessageLocationProviderDefaultImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.nelson.system.personne.web")
public class PersonneWebContext {
	
	@Bean
	public MessageLocationProvider personneWebMessageLocationProvider() {
		return new MessageLocationProviderDefaultImpl("classpath:messages/personne-home");
	}
}
