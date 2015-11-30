package org.nelson.system.tools.test.web.flow.states;

public class ConsultationState extends AbstractPossibleState {

	public ConsultationState() {
		super("consultation");
	}

	public PossibleEvent close() {
		return new PossibleEventImpl("close");
	}
}
