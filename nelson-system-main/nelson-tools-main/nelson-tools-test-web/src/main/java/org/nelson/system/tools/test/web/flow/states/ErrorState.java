package org.nelson.system.tools.test.web.flow.states;

import org.nelson.system.tools.test.web.flow.events.CloseEvent;
import org.nelson.system.tools.test.web.flow.events.PossibleEvent;

public class ErrorState extends AbstractPossibleState {

	public ErrorState() {
		super("error");
	}
	
	public PossibleEvent close() {
		return new CloseEvent();
	}
}
