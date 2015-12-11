package org.nelson.system.personne.web.home.states;

import org.nelson.system.tools.test.web.flow.events.CloseEvent;
import org.nelson.system.tools.test.web.flow.events.PossibleEvent;
import org.nelson.system.tools.test.web.flow.states.AbstractPossibleState;

public class PersonneHomeState extends AbstractPossibleState {

	PersonneHomeState() {
		super("personne-home");
	}
	
	public PossibleEvent close() {
		return new CloseEvent();
	}
}
