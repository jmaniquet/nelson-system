package org.nelson.system.personne.web.consultation;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Ignore;
import org.mockito.Mock;
import org.nelson.system.tools.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockFlowBuilderContext;

@Ignore
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
		registerBean(builderContext, personneConsultationController);
	}
}
