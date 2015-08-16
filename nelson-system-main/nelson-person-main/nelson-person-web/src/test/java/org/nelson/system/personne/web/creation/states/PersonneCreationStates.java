package org.nelson.system.personne.web.creation.states;

import org.nelson.system.tools.test.web.flow.states.CancelState;
import org.nelson.system.tools.test.web.flow.states.FinishState;

public class PersonneCreationStates {

	private PersonneCreationStates() {}
	
	public static final CreationState creation = new CreationState();
	public static final FinishState finish = new FinishState();
	public static final CancelState cancel = new CancelState();
}
