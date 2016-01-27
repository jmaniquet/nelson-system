package org.nelson.system.personne.web.home.states;

import static org.nelson.system.core.web.flow.events.CommonEvent.CLOSE;

import org.nelson.system.core.web.flow.events.PossibleEvent;
import org.nelson.system.core.web.flow.states.AbstractPossibleState;

public class PersonneHomeState extends AbstractPossibleState {

	PersonneHomeState() {
		super("personne-home");
	}
	
	public PossibleEvent close() {
		return CLOSE;
	}
}
