package org.nelson.system.personne.web.consultation;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Test;
import org.mockito.Mock;
import org.nelson.system.core.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.nelson.system.core.test.web.flow.MockExtCtxBuilder;
import org.nelson.system.core.web.NelsonExceptionConfig;
import org.nelson.system.personne.web.consultation.states.PersonneConsultationStates;
import org.nelson.system.personne.web.exception.MissingIdException;
import org.nelson.system.personne.web.exception.UnknownPersonneException;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;

public class PersonneConsultationFlowTest extends AbstractNelsonXmlFlowExecutionTests {
	
	@Mock
	private PersonneConsultationController personneConsultationController;
	
	@Override
	public void setUp() throws Exception {
		initMocks(this);
	}
	
	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return getJarResource(resourceFactory, "/personne/consultation/consultation-flow.xml");
	}
	
	@Override
	protected void configureCustomContextElements(MockFlowBuilderContext builderContext) {
		registerAnnotatedConfig(builderContext, NelsonExceptionConfig.class);
		registerBean(builderContext, personneConsultationController);
	}
	
	@Test
	public void testErrorStateAndCloseError() {
		setCurrentState(PersonneConsultationStates.error);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneConsultationStates.error.closeError())
				.build();
		
		resumeFlow(context);
		
		verifyZeroInteractions(personneConsultationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneConsultationStates.closed);
	}
	
	@Test
	public void testConsultationStateAndClose() {
		setCurrentState(PersonneConsultationStates.consultation);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneConsultationStates.consultation.close())
				.build();
		
		resumeFlow(context);
		
		verifyZeroInteractions(personneConsultationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneConsultationStates.closed);
	}
	
	@Test
	public void testStartFlowWhenIdNull() {
		Long fakeId = null;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		doThrow(new MissingIdException()).when(personneConsultationController).init(null);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneConsultationStates.error);
		verify(personneConsultationController).init(null);
	}
	
	@Test
	public void testStartFlowWhenDoesntExist() {
		Long fakeId = 2L;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		doThrow(new UnknownPersonneException(fakeId)).when(personneConsultationController).init(fakeId);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneConsultationStates.error);
		verify(personneConsultationController).init(fakeId);
	}
	
	@Test
	public void testStartFlowWhenPersonneExists() {
		Long fakeId = 1L;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneConsultationStates.consultation);
		verify(personneConsultationController).init(fakeId);
	}
}
