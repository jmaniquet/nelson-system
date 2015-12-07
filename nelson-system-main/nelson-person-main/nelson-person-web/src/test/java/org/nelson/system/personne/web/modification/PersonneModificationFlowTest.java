package org.nelson.system.personne.web.modification;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.personne.web.consultation.states.PersonneConsultationStates;
import org.nelson.system.personne.web.modification.states.PersonneModificationStates;
import org.nelson.system.tools.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.nelson.system.tools.test.web.flow.MockExtCtxBuilder;
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
		registerAnnotatedConfig(builderContext, PersonneModificationConfig.class);
		registerBean(builderContext, personneModificationController);
	}
	
	@Test
	public void testStartFlowWhenIdNull() {
		Long fakeId = null;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		Mockito.doThrow(new MissingIdException()).when(personneModificationController).init(null);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneModificationStates.error);
		Mockito.verify(personneModificationController).init(null);
	}
	
	@Test
	public void testStartFlowWhenDoesntExist() {
		Long fakeId = 2L;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		Mockito.doThrow(new UnknownPersonneException(fakeId)).when(personneModificationController).init(fakeId);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneModificationStates.error);
		Mockito.verify(personneModificationController).init(fakeId);
	}
	
	@Test
	public void testStartFlowWhenPersonneExists() {
		Long fakeId = 1L;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneModificationStates.modification);
		Mockito.verify(personneModificationController).init(fakeId);
	}
	
	@Test
	public void testErrorStateAndClose() {
		setCurrentState(PersonneConsultationStates.error);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneConsultationStates.error.close())
				.build();
		
		resumeFlow(context);
		
		Mockito.verifyZeroInteractions(personneModificationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneModificationStates.cancel);
	}
	
	@Test
	public void testModificationStateAndCancel() {
		setCurrentState(PersonneModificationStates.modification);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneModificationStates.modification.cancel())
				.build();
		
		resumeFlow(context);
		
		Mockito.verifyZeroInteractions(personneModificationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneModificationStates.cancel);
	}
	
	@Test
	public void testModificationStateAndFinish() {
		Long fakeId = 243L;
		Personne fakePersonne = new Personne();
		fakePersonne.setId(fakeId);
		
		Mockito.when(personneModificationController.getPersonne()).thenReturn(fakePersonne);
		
		setCurrentState(PersonneModificationStates.modification);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneModificationStates.modification.finish())
				.build();
		
		resumeFlow(context);
		
		assertFlowExecutionEndedWithOutcome(PersonneModificationStates.finish);
		
		Mockito.verify(personneModificationController).update();;
	}
}
