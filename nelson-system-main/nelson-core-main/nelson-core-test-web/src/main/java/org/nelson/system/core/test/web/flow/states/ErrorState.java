package org.nelson.system.core.test.web.flow.states;

import static org.nelson.system.core.test.web.flow.events.CommonEvent.CLOSE;

import org.nelson.system.core.test.web.flow.events.PossibleEvent;

public class ErrorState extends AbstractPossibleState {

	public ErrorState() {
		super("error");
	}
	
	public PossibleEvent close() {
		return CLOSE;
	}
}
