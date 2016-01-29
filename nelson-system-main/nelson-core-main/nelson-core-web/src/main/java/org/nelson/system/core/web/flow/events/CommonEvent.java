package org.nelson.system.core.web.flow.events;

public enum CommonEvent implements PossibleEvent {
	CANCEL("cancel"),
	CLOSE("close"),
	CLOSE_ERROR("close-error"),
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
