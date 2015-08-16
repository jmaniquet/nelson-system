package org.nelson.system.core.web.context;

import org.nelson.system.core.api.basenames.MessageLocationProvider;
import org.nelson.system.core.api.basenames.MessageLocationProviderDefaultImpl;
import org.nelson.system.core.web.CoreWebScannable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CoreWebScannable.class)
public class CoreWebContext {
	
	@Bean
	public MessageLocationProvider coreWebMessageLocationProvider() {
		return new MessageLocationProviderDefaultImpl(
				"classpath:messages/template/default-layout",
				"classpath:messages/template/header",
				"classpath:messages/template/footer");
	}
}
