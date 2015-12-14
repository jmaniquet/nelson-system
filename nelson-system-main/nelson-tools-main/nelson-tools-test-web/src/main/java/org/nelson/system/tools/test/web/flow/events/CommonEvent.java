package org.nelson.system.tools.test.web.flow.events;

public enum CommonEvent implements PossibleEvent {
	CANCEL("cancel"),
	CLOSE("close"),
	FINISH("finish");
	
	private String eventId;
	
	private CommonEvent(String eventId) {
		this.eventId = eventId;
	}

	@Override
	public String getEventId() {
		return this.eventId;
	}
}
