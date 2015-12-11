package org.nelson.system.tools.test.web.flow.states;

public class ErrorState extends AbstractPossibleState {

	public ErrorState() {
		super("error");
	}
	
	public PossibleEvent close() {
		return new PossibleEventImpl("close");
	}
}
