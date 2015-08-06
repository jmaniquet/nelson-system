package org.nelson.system.webapp.context;

import java.util.ArrayList;
import java.util.List;

import org.nelson.system.core.api.basenames.MessageLocationProvider;
import org.nelson.system.core.api.basenames.MessageLocationProviderDefaultImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.faces.config.AbstractFacesFlowConfiguration;
import org.springframework.faces.webflow.FlowFacesContextLifecycleListener;
import org.springframework.faces.webflow.JsfFlowHandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.execution.FlowExecutionListener;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.springframework.webflow.scope.ScopeRegistrar;

@Configuration
public class WebappContext extends AbstractFacesFlowConfiguration {
	
	@Bean
	public ScopeRegistrar scopeRegistrar() {
		return new ScopeRegistrar();
	}
	
	@Bean
	public FlowExecutor flowExecutor() {
		return getFlowExecutorBuilder(flowRegistry())
				.addFlowExecutionListener(new FlowFacesContextLifecycleListener())
				.addFlowExecutionListener(autowiringTransientPropertiesFlowExecutionListener())
				.build();
	}

	@Bean
	public FlowExecutionListener autowiringTransientPropertiesFlowExecutionListener() {
		return new AutowiringTransientPropertiesFlowExecutionListener();
	}

	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder(flowBuilderServices())
				.setBasePath("/WEB-INF/views")
				.addFlowLocationPattern("/**/*-flow.xml")
				.setParent(flowRegistryClasspath())
				.build();
	}
	
	@Bean
	public FlowDefinitionRegistry flowRegistryClasspath() {
		return getFlowDefinitionRegistryBuilder(flowBuilderServices())
				.setBasePath("classpath*:META-INF/views")
				.addFlowLocationPattern("/**/*-flow.xml")
				.build();
	}
	
	@Bean
	public FlowBuilderServices flowBuilderServices() {
		return getFlowBuilderServicesBuilder().build();
	}
	
	@Bean
	public HandlerMapping flowHandlerMapping() {
		FlowHandlerMapping m = new FlowHandlerMapping();
		m.setFlowRegistry(flowRegistry());
		return m;
	}
	
	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		JsfFlowHandlerAdapter a = new JsfFlowHandlerAdapter();
		a.setFlowExecutor(flowExecutor());
		return a;
	}
	
	@Bean
	public MessageLocationProvider webappMessageLocationProvider() {
		return new MessageLocationProviderDefaultImpl("classpath:messages/home");
	}
	
	@Bean
	@Autowired
	public MessageSource messageSource(List<MessageLocationProvider> messageLocationProviders) {
		List<String> baseNamesList = new ArrayList<>();
		for (MessageLocationProvider messageSourceProvider : messageLocationProviders) {
			String [] baseNamesTab = messageSourceProvider.getBaseNames();
			for (String baseName : baseNamesTab) {
				baseNamesList.add(baseName);
			}
		}
		
		String [] finalTab = baseNamesList.toArray(new String[baseNamesList.size()]);
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(finalTab);
		return messageSource;
	}
}
