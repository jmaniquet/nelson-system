package org.nelson.system.core.test.web.flow.states;

import static org.nelson.system.core.test.web.flow.events.CommonEvent.CLOSE;

import org.nelson.system.core.test.web.flow.events.PossibleEvent;

public class ConsultationState extends AbstractPossibleState {

	public ConsultationState() {
		super("consultation");
	}

	public PossibleEvent close() {
		return CLOSE;
	}
}
