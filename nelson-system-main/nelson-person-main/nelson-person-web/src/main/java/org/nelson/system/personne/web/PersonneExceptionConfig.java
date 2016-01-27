package org.nelson.system.personne.web;

import org.nelson.system.core.api.exception.NelsonException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.engine.FlowExecutionExceptionHandler;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;

@Configuration
public class PersonneExceptionConfig {

	@Bean
	public FlowExecutionExceptionHandler personneExceptionHandler() {
		TransitionExecutingFlowExecutionExceptionHandler feeh = new TransitionExecutingFlowExecutionExceptionHandler();
		feeh.add(NelsonException.class, "error");
		return feeh;
	}
}
