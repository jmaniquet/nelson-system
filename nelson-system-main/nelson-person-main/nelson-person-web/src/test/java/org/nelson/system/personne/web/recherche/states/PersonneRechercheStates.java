package org.nelson.system.personne.web.recherche.states;

import org.nelson.system.tools.test.web.flow.states.CloseState;

public class PersonneRechercheStates {

	private PersonneRechercheStates() {}
	
	public static final RechercheState recherche = new RechercheState();
	public static final CloseState close = new CloseState();
}
