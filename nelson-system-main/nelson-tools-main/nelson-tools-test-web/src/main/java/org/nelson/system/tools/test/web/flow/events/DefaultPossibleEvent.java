package org.nelson.system.tools.test.web.flow.events;

public class DefaultPossibleEvent implements PossibleEvent {

	private String eventId;
	
	public DefaultPossibleEvent(String eventId) {
		super();
		this.eventId = eventId;
	}

	@Override
	public String getEventId() {
		return this.eventId;
	}
}
