package org.nelson.system.core.web.flow.states;

import static org.nelson.system.core.web.flow.events.CommonEvent.CLOSE;

import org.nelson.system.core.web.flow.events.PossibleEvent;

public class ConsultationState extends AbstractPossibleState {

	public ConsultationState() {
		super("consultation");
	}

	public PossibleEvent close() {
		return CLOSE;
	}
}
