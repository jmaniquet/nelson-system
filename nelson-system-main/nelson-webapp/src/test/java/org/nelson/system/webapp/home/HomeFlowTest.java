package org.nelson.system.webapp.home;

import org.junit.Test;
import org.nelson.system.tools.test.web.flow.AbstractNelsonXmlFlowExecutionTests;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;

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
}
