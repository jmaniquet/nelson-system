package org.nelson.system.core.test.web.flow;

import org.nelson.system.core.test.web.flow.states.PossibleState;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.FlowExecutionException;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

public abstract class AbstractNelsonXmlFlowExecutionTests extends AbstractXmlFlowExecutionTests {

	@Override
	protected FlowDefinitionResource[] getModelResources(FlowDefinitionResourceFactory resourceFactory) {
		return new FlowDefinitionResource[] {
				resourceFactory.createResource("/META-INF/views/default/default-flow.xml", null, "default")
		};
	}
	
	protected void assertCurrentStateEquals(PossibleState state) {
		super.assertCurrentStateEquals(state.getStateId());
	}
	
	protected void assertFlowExecutionEndedWithOutcome(PossibleState state) {
		assertFlowExecutionEnded();
		assertFlowExecutionOutcomeEquals(state.getStateId());
	}
	
	protected void assertFlowExecutionOutcomeEquals(PossibleState state) {
		assertFlowExecutionOutcomeEquals(state.getStateId());
	}
	
	protected FlowDefinitionResource getJarResource(FlowDefinitionResourceFactory resourceFactory, String flowPath) {
		return resourceFactory.createFileResource("src/main/resources/META-INF/views" + flowPath);
	}
	
	protected FlowDefinitionResource getWarResource(FlowDefinitionResourceFactory resourceFactory, String flowPath) {
		return resourceFactory.createFileResource("src/main/webapp/WEB-INF/views" + flowPath);
	}
	
	protected void registerAnnotatedConfig(MockFlowBuilderContext builderContext, Class<?>... annotatedClasses) {
		builderContext.getFlowBuilderServices().setApplicationContext(new AnnotationConfigApplicationContext(annotatedClasses));
	}
	
	protected <I> void registerBean(MockFlowBuilderContext builderContext, I interfacee) {
		Class<?> clazz = interfacee.getClass();
		String interfaceName = clazz.getSimpleName();
		if (ClassUtils.isCglibProxy(interfacee)) {
			interfaceName = interfaceName.substring(0, interfaceName.indexOf("$"));
		}
		String uncapitalizeInterfaceName = StringUtils.uncapitalize(interfaceName);
		builderContext.registerBean(uncapitalizeInterfaceName, interfacee);
	}
	
	protected void setCurrentState(PossibleState state) {
		super.setCurrentState(state.getStateId());
	}
	
	/**
	 * Start flow with empty input map and empty mock context
	 */
	protected void startFlow()  throws FlowExecutionException {
		MutableAttributeMap<?> input = new LocalAttributeMap<>();
		MockExternalContext context = new MockExternalContext();
		startFlow(input, context);
	}
	
	/**
	 * Start flow with empty mock context
	 */
	protected void startFlow(MutableAttributeMap<?> input) throws FlowExecutionException {
		MockExternalContext context = new MockExternalContext();
		startFlow(input, context);
	}
}
