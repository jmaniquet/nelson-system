package org.nelson.system.personne.web.home.states;

import org.nelson.system.tools.test.web.flow.states.AbstractPossibleState;
import org.nelson.system.tools.test.web.flow.states.PossibleEvent;
import org.nelson.system.tools.test.web.flow.states.PossibleEventImpl;

public class PersonneHomeState extends AbstractPossibleState {

	PersonneHomeState() {
		super("personne-home");
	}
	
	public PossibleEvent close() {
		return new PossibleEventImpl("close");
	}
}
