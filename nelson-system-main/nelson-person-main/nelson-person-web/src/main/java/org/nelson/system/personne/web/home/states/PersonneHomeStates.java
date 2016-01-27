package org.nelson.system.personne.web.home.states;

import org.nelson.system.core.web.flow.states.ClosedState;

public class PersonneHomeStates {

	private PersonneHomeStates() {}
	
	public static final PersonneHomeState home = new PersonneHomeState();
	public static final ClosedState closed = new ClosedState();
}
