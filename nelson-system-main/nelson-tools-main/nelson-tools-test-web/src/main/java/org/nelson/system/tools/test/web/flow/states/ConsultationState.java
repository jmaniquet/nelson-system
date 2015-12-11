package org.nelson.system.tools.test.web.flow.states;

import org.nelson.system.tools.test.web.flow.events.CloseEvent;
import org.nelson.system.tools.test.web.flow.events.PossibleEvent;

public class ConsultationState extends AbstractPossibleState {

	public ConsultationState() {
		super("consultation");
	}

	public PossibleEvent close() {
		return new CloseEvent();
	}
}
