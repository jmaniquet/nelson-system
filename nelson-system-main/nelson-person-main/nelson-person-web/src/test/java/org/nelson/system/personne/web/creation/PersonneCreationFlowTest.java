package org.nelson.system.personne.web.creation;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.nelson.system.personne.web.creation.states.PersonneCreationStates;
import org.nelson.system.tools.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.nelson.system.tools.test.web.flow.MockExtCtxBuilder;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;

public class PersonneCreationFlowTest extends AbstractNelsonXmlFlowExecutionTests {
	
	@Mock
	private PersonneCreationController personneCreationController;
	
	@Override
	public void setUp() throws Exception {
		initMocks(this);
	}
	
	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return getJarResource(resourceFactory, "/personne/creation/creation-flow.xml");
	}
	
	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
		registerBean(builderContext, personneCreationController);
	}
	
	@Test
	public void testStartFlow() {
		startFlow();
		assertCurrentStateEquals(PersonneCreationStates.creation);
		Mockito.verify(personneCreationController).init();
	}
	
	@Test
	public void testFinish() {
		setCurrentState(PersonneCreationStates.creation);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneCreationStates.creation.finish())
				.build();
		
		resumeFlow(context);
		
		assertFlowExecutionEndedWithOutcome(PersonneCreationStates.finish);
		
		Mockito.verify(personneCreationController).create();
	}
	
	@Test
	public void testCancel() {
		setCurrentState(PersonneCreationStates.creation);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneCreationStates.creation.cancel())
				.build();
		
		resumeFlow(context);
		
		Mockito.verifyZeroInteractions(personneCreationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneCreationStates.cancel);
	}
}
