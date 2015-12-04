package org.nelson.system.personne.web.consultation;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.nelson.system.personne.web.consultation.states.PersonneConsultationStates;
import org.nelson.system.tools.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.nelson.system.tools.test.web.flow.MockExtCtxBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
		builderContext.getFlowBuilderServices().setApplicationContext(new AnnotationConfigApplicationContext(PersonneConsultationConfig.class));
		registerBean(builderContext, personneConsultationController);
	}
	
	@Test
	public void testErrorStateAndClose() {
		setCurrentState(PersonneConsultationStates.error);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneConsultationStates.error.close())
				.build();
		
		resumeFlow(context);
		
		Mockito.verifyZeroInteractions(personneConsultationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneConsultationStates.close);
	}
	
	@Test
	public void testConsultationStateAndClose() {
		setCurrentState(PersonneConsultationStates.consultation);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneConsultationStates.consultation.close())
				.build();
		
		resumeFlow(context);
		
		Mockito.verifyZeroInteractions(personneConsultationController);
		
		assertFlowExecutionEndedWithOutcome(PersonneConsultationStates.close);
	}
	
	@Test
	public void testStartFlowWhenIdNull() {
		Long fakeId = null;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		Mockito.doThrow(new MissingIdException()).when(personneConsultationController).init(null);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneConsultationStates.error);
		Mockito.verify(personneConsultationController).init(null);
	}
	
	@Test
	public void testStartFlowWhenDoesntExist() {
		Long fakeId = 2L;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		Mockito.doThrow(new UnknownPersonneException(fakeId)).when(personneConsultationController).init(fakeId);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneConsultationStates.error);
		Mockito.verify(personneConsultationController).init(fakeId);
	}
	
	@Test
	public void testStartFlowWhenPersonneExists() {
		Long fakeId = 1L;
		
		MutableAttributeMap<Long> input = new LocalAttributeMap<Long>();
		input.put("id", fakeId);
		
		startFlow(input);
		assertCurrentStateEquals(PersonneConsultationStates.consultation);
		Mockito.verify(personneConsultationController).init(fakeId);
	}
}
