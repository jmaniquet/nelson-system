package org.nelson.system.webapp.home;

import org.junit.Test;
import org.nelson.system.core.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.nelson.system.core.test.web.flow.MockExtCtxBuilder;
import org.nelson.system.core.web.flow.events.CommonEvent;
import org.nelson.system.core.web.flow.states.ClosedState;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;

public class HomeFlowTest extends AbstractNelsonXmlFlowExecutionTests {
	
	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return getWarResource(resourceFactory, "/home/home-flow.xml");
	}
	
	@Test
	public void testStartFlow() {
		startFlow();
		assertCurrentStateEquals("home");
	}
	
	@Test
	public void testHomeStateAndClose() {
		setCurrentState("home");
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(CommonEvent.CLOSE)
				.build();
		
		resumeFlow(context);
		
		assertFlowExecutionEndedWithOutcome(new ClosedState());
	}
}
