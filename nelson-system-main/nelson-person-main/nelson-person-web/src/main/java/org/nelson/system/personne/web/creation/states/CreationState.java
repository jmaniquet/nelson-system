package org.nelson.system.personne.web.creation.states;

import static org.nelson.system.core.web.flow.events.CommonEvent.CANCEL;
import static org.nelson.system.core.web.flow.events.CommonEvent.FINISH;

import org.nelson.system.core.web.flow.events.PossibleEvent;
import org.nelson.system.core.web.flow.states.AbstractPossibleState;

public class CreationState extends AbstractPossibleState {

	CreationState() {
		super("creation");
	}
	
	public PossibleEvent finish() {
		return FINISH;
	}

	public PossibleEvent cancel() {
		return CANCEL;
	}
}
