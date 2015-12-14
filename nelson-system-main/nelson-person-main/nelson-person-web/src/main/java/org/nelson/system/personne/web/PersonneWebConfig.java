package org.nelson.system.personne.web;

import org.nelson.system.core.api.basenames.MessageLocationProvider;
import org.nelson.system.core.api.basenames.MessageLocationProviderDefaultImpl;
import org.nelson.system.personne.api.PersonneApiConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersonneApiConfig.class)
@ComponentScan
public class PersonneWebConfig {
	
	@Bean
	public MessageLocationProvider personneWebMessageLocationProvider() {
		return new MessageLocationProviderDefaultImpl(
				"classpath:messages/personne-home",
				"classpath:messages/personne-error",
				"classpath:messages/personne-consultation",
				"classpath:messages/personne-creation",
				"classpath:messages/personne-modification",
				"classpath:messages/personne-recherche");
	}
}
