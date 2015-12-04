package org.nelson.system.personne.web.consultation.states;

import org.nelson.system.tools.test.web.flow.states.AbstractPossibleState;
import org.nelson.system.tools.test.web.flow.states.PossibleEvent;
import org.nelson.system.tools.test.web.flow.states.PossibleEventImpl;

public class ErrorState extends AbstractPossibleState {

	ErrorState() {
		super("error");
	}
	
	public PossibleEvent close() {
		return new PossibleEventImpl("close");
	}
}
