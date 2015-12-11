package org.nelson.system.personne.web.modification.states;

import org.nelson.system.tools.test.web.flow.events.PossibleEvent;
import org.nelson.system.tools.test.web.flow.events.PossibleEventImpl;
import org.nelson.system.tools.test.web.flow.states.AbstractPossibleState;

public class ModificationState extends AbstractPossibleState {

	ModificationState() {
		super("modification");
	}
	
	public PossibleEvent finish() {
		return new PossibleEventImpl("finish");
	}

	public PossibleEvent cancel() {
		return new PossibleEventImpl("cancel");
	}
}
