package org.nelson.system.personne.web.recherche.states;

import org.nelson.system.tools.test.web.flow.events.CloseEvent;
import org.nelson.system.tools.test.web.flow.events.PossibleEvent;
import org.nelson.system.tools.test.web.flow.states.AbstractPossibleState;

public class RechercheState extends AbstractPossibleState {

	RechercheState() {
		super("recherche");
	}
	
	public PossibleEvent close() {
		return new CloseEvent();
	}
}
