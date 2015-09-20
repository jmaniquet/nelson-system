package org.nelson.system.personne.web.recherche.states;

import org.nelson.system.tools.test.web.flow.states.AbstractPossibleState;
import org.nelson.system.tools.test.web.flow.states.PossibleEvent;
import org.nelson.system.tools.test.web.flow.states.PossibleEventImpl;

public class RechercheState extends AbstractPossibleState {

	RechercheState() {
		super("recherche");
	}
	
	public PossibleEvent close() {
		return new PossibleEventImpl("close");
	}
}
