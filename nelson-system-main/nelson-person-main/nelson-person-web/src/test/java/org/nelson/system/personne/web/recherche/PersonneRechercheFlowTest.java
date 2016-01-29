package org.nelson.system.personne.web.recherche;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Test;
import org.mockito.Mock;
import org.nelson.system.core.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.nelson.system.core.test.web.flow.MockExtCtxBuilder;
import org.nelson.system.personne.web.recherche.states.PersonneRechercheStates;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;

public class PersonneRechercheFlowTest extends AbstractNelsonXmlFlowExecutionTests {
	
	@Mock
	private PersonneRechercheController personneRechercheController;
	
	@Override
	public void setUp() throws Exception {
		initMocks(this);
	}
	
	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		return getJarResource(resourceFactory, "/personne/recherche/recherche-flow.xml");
	}
	
	@Override
	protected void configureCustomContextElements(MockFlowBuilderContext builderContext) {
		registerBean(builderContext, personneRechercheController);
	}
	
	@Test
	public void testStartFlow() {
		startFlow();
		assertCurrentStateEquals(PersonneRechercheStates.recherche);
		verify(personneRechercheController).init();
	}
	
	public void testClose() {
		setCurrentState(PersonneRechercheStates.recherche);
		MockExternalContext context = MockExtCtxBuilder
				.builder()
				.withEvent(PersonneRechercheStates.recherche.close())
				.build();
		
		resumeFlow(context);
		
		assertFlowExecutionEndedWithOutcome(PersonneRechercheStates.closed);
	}
}
