package org.nelson.system.tools.test.web.flow.states;

import org.nelson.system.tools.test.web.flow.events.PossibleEvent;
import org.nelson.system.tools.test.web.flow.events.PossibleEventImpl;

public class ErrorState extends AbstractPossibleState {

	public ErrorState() {
		super("error");
	}
	
	public PossibleEvent close() {
		return new PossibleEventImpl("close");
	}
}
