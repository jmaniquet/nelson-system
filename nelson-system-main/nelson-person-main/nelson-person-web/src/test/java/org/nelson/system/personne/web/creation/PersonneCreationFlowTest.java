package org.nelson.system.personne.web.creation;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Test;
import org.mockito.Mock;
import org.nelson.system.core.db.personne.domain.Personne;
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
		verify(personneCreationController).init();
	}
	
	@Test
	public void testFinish() {
		Long fakeId = 243L;
		Personne fakePersonne = new Personne();
		fakePersonne.setId(fakeId);
		
		when(personneCreationController.getPersonne()).thenReturn(fakePersonne);
		
		setCurrentState(PersonneCreationStates.creation);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneCreationStates.creation.finish())
				.build();
		
		resumeFlow(context);
		
		assertFlowExecutionEndedWithOutcome(PersonneCreationStates.finished);
		
		verify(personneCreationController).create();
	}
	
	@Test
	public void testCancel() {
		setCurrentState(PersonneCreationStates.creation);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneCreationStates.creation.cancel())
				.build();
		
		resumeFlow(context);
		
		verifyZeroInteractions(personneCreationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneCreationStates.canceled);
	}
}
