package org.nelson.system.personne.web.modification;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Test;
import org.mockito.Mock;
import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.core.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.nelson.system.core.test.web.flow.MockExtCtxBuilder;
import org.nelson.system.core.web.NelsonExceptionConfig;
import org.nelson.system.personne.web.exception.MissingIdException;
import org.nelson.system.personne.web.exception.UnknownPersonneException;
import org.nelson.system.personne.web.modification.states.PersonneModificationStates;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;

public class PersonneModificationFlowTest extends AbstractNelsonXmlFlowExecutionTests {
	
	@Mock
	private PersonneModificationController personneModificationController;
	
	@Override
	public void setUp() throws Exception {
		initMocks(this);
	}
	
	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return getJarResource(resourceFactory, "/personne/modification/modification-flow.xml");
	}
	
	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
		registerAnnotatedConfig(builderContext, NelsonExceptionConfig.class);
		registerBean(builderContext, personneModificationController);
	}
	
	@Test
	public void testStartFlowWhenIdNull() {
		Long fakeId = null;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		doThrow(new MissingIdException()).when(personneModificationController).init(null);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneModificationStates.error);
		verify(personneModificationController).init(null);
	}
	
	@Test
	public void testStartFlowWhenDoesntExist() {
		Long fakeId = 2L;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		doThrow(new UnknownPersonneException(fakeId)).when(personneModificationController).init(fakeId);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneModificationStates.error);
		verify(personneModificationController).init(fakeId);
	}
	
	@Test
	public void testStartFlowWhenPersonneExists() {
		Long fakeId = 1L;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneModificationStates.modification);
		verify(personneModificationController).init(fakeId);
	}
	
	@Test
	public void testErrorStateAndCloseError() {
		setCurrentState(PersonneModificationStates.error);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneModificationStates.error.closeError())
				.build();
		
		resumeFlow(context);
		
		verifyZeroInteractions(personneModificationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneModificationStates.canceled);
	}
	
	@Test
	public void testModificationStateAndCancel() {
		setCurrentState(PersonneModificationStates.modification);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneModificationStates.modification.cancel())
				.build();
		
		resumeFlow(context);
		
		verifyZeroInteractions(personneModificationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneModificationStates.canceled);
	}
	
	@Test
	public void testModificationStateAndFinish() {
		Long fakeId = 243L;
		Personne fakePersonne = new Personne();
		fakePersonne.setId(fakeId);
		
		when(personneModificationController.getPersonne()).thenReturn(fakePersonne);
		
		setCurrentState(PersonneModificationStates.modification);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneModificationStates.modification.finish())
				.build();
		
		resumeFlow(context);
		
		assertFlowExecutionEndedWithOutcome(PersonneModificationStates.finished);
		
		verify(personneModificationController).update();
	}
}
