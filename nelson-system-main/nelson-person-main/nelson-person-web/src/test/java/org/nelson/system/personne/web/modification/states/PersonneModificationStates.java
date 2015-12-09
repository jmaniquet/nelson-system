package org.nelson.system.personne.web.modification.states;

import org.nelson.system.tools.test.web.flow.states.CancelState;
import org.nelson.system.tools.test.web.flow.states.FinishedState;

public class PersonneModificationStates {

	private PersonneModificationStates() {}
	
	public static final ModificationState modification = new ModificationState();
	public static final ErrorState error = new ErrorState();
	public static final FinishedState finished = new FinishedState();
	public static final CancelState cancel = new CancelState();
}
