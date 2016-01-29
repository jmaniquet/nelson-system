package org.nelson.system.core.web;

import org.nelson.system.core.api.exception.NelsonException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.engine.FlowExecutionExceptionHandler;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;

@Configuration
public class NelsonExceptionConfig {

	@Bean
	public FlowExecutionExceptionHandler nelsonExceptionHandler() {
		TransitionExecutingFlowExecutionExceptionHandler feeh = new TransitionExecutingFlowExecutionExceptionHandler();
		feeh.add(NelsonException.class, "error");
		return feeh;
	}
}
