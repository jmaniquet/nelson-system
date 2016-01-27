package org.nelson.system.personne.web.creation.states;

import org.nelson.system.core.web.flow.states.CanceledState;
import org.nelson.system.core.web.flow.states.FinishedState;

public class PersonneCreationStates {

	private PersonneCreationStates() {}
	
	public static final CreationState creation = new CreationState();
	public static final FinishedState finished = new FinishedState();
	public static final CanceledState canceled = new CanceledState();
}
