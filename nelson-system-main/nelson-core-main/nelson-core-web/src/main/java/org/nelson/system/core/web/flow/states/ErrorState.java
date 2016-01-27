package org.nelson.system.core.web.flow.states;

import static org.nelson.system.core.web.flow.events.CommonEvent.CLOSE_ERROR;

import org.nelson.system.core.web.flow.events.PossibleEvent;

public class ErrorState extends AbstractPossibleState {

	public ErrorState() {
		super("error");
	}
	
	public PossibleEvent closeError() {
		return CLOSE_ERROR;
	}
}
