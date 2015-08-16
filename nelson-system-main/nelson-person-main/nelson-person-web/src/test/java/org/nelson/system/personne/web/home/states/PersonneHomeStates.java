package org.nelson.system.personne.web.home.states;

import org.nelson.system.tools.test.web.flow.states.CloseState;

public class PersonneHomeStates {

	private PersonneHomeStates() {}
	
	public static final PersonneHomeState home = new PersonneHomeState();
	public static final CloseState close = new CloseState();
}
