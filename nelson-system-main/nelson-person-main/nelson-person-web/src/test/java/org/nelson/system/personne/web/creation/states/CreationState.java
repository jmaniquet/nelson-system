package org.nelson.system.personne.web.creation.states;

import org.nelson.system.tools.test.web.flow.states.AbstractPossibleState;
import org.nelson.system.tools.test.web.flow.states.PossibleEvent;
import org.nelson.system.tools.test.web.flow.states.PossibleEventImpl;

public class CreationState extends AbstractPossibleState {

	CreationState() {
		super("creation");
	}
	
	public PossibleEvent finish() {
		return new PossibleEventImpl("finish");
	}

	public PossibleEvent cancel() {
		return new PossibleEventImpl("cancel");
	}
}
