package org.nelson.system.personne.web.home;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Test;
import org.nelson.system.personne.web.home.states.PersonneHomeStates;
import org.nelson.system.tools.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.nelson.system.tools.test.web.flow.MockExtCtxBuilder;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;

public class PersonneHomeFlowTest extends AbstractNelsonXmlFlowExecutionTests {
	
	@Override
	public void setUp() throws Exception {
		initMocks(this);
	}
	
	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return getJarResource(resourceFactory, "/personne/home/personne-home-flow.xml");
	}
	
	@Test
	public void testStartFlow() {
		startFlow();
		assertCurrentStateEquals(PersonneHomeStates.home);
	}
	
	@Test
	public void testClose() {
		setCurrentState(PersonneHomeStates.home);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneHomeStates.home.close())
				.build();
		
		resumeFlow(context);
		
		assertFlowExecutionEndedWithOutcome(PersonneHomeStates.closed);
	}
}
