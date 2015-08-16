package org.nelson.system.tools.test.web.flow.states;

public class PossibleEventImpl implements PossibleEvent {

	private String eventId;
	
	public PossibleEventImpl(String eventId) {
		super();
		this.eventId = eventId;
	}

	@Override
	public String getEventId() {
		return this.eventId;
	}

}
