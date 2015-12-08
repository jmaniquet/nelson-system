package org.nelson.system.personne.web;

import org.nelson.system.personne.web.exception.MissingIdException;
import org.nelson.system.personne.web.exception.UnknownPersonneException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.engine.FlowExecutionExceptionHandler;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;

@Configuration
public class PersonneExceptionConfig {

	@Bean
	public FlowExecutionExceptionHandler personneExceptionHandler() {
		TransitionExecutingFlowExecutionExceptionHandler feeh = new TransitionExecutingFlowExecutionExceptionHandler();
		feeh.add(MissingIdException.class, "error");
		feeh.add(UnknownPersonneException.class, "error");
		return feeh;
	}
}
