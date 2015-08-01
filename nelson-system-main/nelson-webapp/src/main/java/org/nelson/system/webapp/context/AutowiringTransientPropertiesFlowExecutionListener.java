package org.nelson.system.webapp.context;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.webflow.definition.TransitionDefinition;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.RequestContext;

/**
 * Fix pour https://jira.springsource.org/browse/SWF-1224. Le listener re
 * autowire les beans des scopes Flow/View/Flash de spring webflow.
 * 
 */
public class AutowiringTransientPropertiesFlowExecutionListener extends FlowExecutionListenerAdapter implements BeanFactoryAware {
	
	private AutowireCapableBeanFactory beanFactory;

	@Override
	public void resuming(RequestContext context) {
		autowireContext(context);
	}

	@Override
	public void transitionExecuting(RequestContext context, TransitionDefinition transition) {
		autowireContext(context);
	}

	private void autowireContext(RequestContext context) {
		for (Object o : context.getFlowScope().asMap().values()) {
			if (o != null) {
				beanFactory.autowireBean(o);
			}
		}
		for (Object o : context.getFlashScope().asMap().values()) {
			if (o != null) {
				beanFactory.autowireBean(o);
			}
		}
		if (context.inViewState()) {
			for (Object o : context.getViewScope().asMap().values()) {
				if (o != null) {
					beanFactory.autowireBean(o);
				}
			}
		}
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (AutowireCapableBeanFactory) beanFactory;
	}

}
