package org.nelson.system.core.test.web.flow;

import org.nelson.system.core.web.flow.events.PossibleEvent;
import org.springframework.webflow.test.MockExternalContext;

public class MockExtCtxBuilder {

	private MockExternalContext context;
	
	public static MockExtCtxBuilder builder() {
		return new MockExtCtxBuilder();
	}
	
	private MockExtCtxBuilder() {
		context = new MockExternalContext();
	}
	
	public MockExtCtxBuilder withEvent(String eventId) {
		context.setEventId(eventId);
		return this;
	}
	
	public MockExtCtxBuilder withEvent(PossibleEvent event) {
		context.setEventId(event.getEventId());
		return this;
	}
	
	public MockExternalContext build() {
		return context;
	}
}
