package org.nelson.system.personne.web.modification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.engine.FlowExecutionExceptionHandler;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;

@Configuration
public class PersonneModificationConfig {

	@Bean
	public FlowExecutionExceptionHandler personneModificationExceptionHandler() {
		TransitionExecutingFlowExecutionExceptionHandler feeh = new TransitionExecutingFlowExecutionExceptionHandler();
		feeh.add(MissingIdException.class, "error");
		feeh.add(UnknownPersonneException.class, "error");
		return feeh;
	}
}
