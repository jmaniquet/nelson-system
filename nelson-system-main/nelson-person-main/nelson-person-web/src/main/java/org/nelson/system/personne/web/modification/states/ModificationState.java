package org.nelson.system.personne.web.modification.states;

import static org.nelson.system.core.web.flow.events.CommonEvent.CANCEL;
import static org.nelson.system.core.web.flow.events.CommonEvent.FINISH;

import org.nelson.system.core.web.flow.events.PossibleEvent;
import org.nelson.system.core.web.flow.states.AbstractPossibleState;

public class ModificationState extends AbstractPossibleState {

	ModificationState() {
		super("modification");
	}
	
	public PossibleEvent finish() {
		return FINISH;
	}

	public PossibleEvent cancel() {
		return CANCEL;
	}
}
