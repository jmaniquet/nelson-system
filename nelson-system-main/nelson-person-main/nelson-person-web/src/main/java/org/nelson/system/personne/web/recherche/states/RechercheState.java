package org.nelson.system.personne.web.recherche.states;

import static org.nelson.system.core.web.flow.events.CommonEvent.CLOSE;

import org.nelson.system.core.web.flow.events.PossibleEvent;
import org.nelson.system.core.web.flow.states.AbstractPossibleState;

public class RechercheState extends AbstractPossibleState {

	RechercheState() {
		super("recherche");
	}
	
	public PossibleEvent close() {
		return CLOSE;
	}
}
