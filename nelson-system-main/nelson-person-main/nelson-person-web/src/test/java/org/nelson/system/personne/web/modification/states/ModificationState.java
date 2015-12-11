package org.nelson.system.personne.web.modification.states;

import org.nelson.system.tools.test.web.flow.events.CancelEvent;
import org.nelson.system.tools.test.web.flow.events.FinishEvent;
import org.nelson.system.tools.test.web.flow.events.PossibleEvent;
import org.nelson.system.tools.test.web.flow.states.AbstractPossibleState;

public class ModificationState extends AbstractPossibleState {

	ModificationState() {
		super("modification");
	}
	
	public PossibleEvent finish() {
		return new FinishEvent();
	}

	public PossibleEvent cancel() {
		return new CancelEvent();
	}
}
