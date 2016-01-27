package org.nelson.system.core.web.flow.states;

public abstract class AbstractPossibleState implements PossibleState {

	private String stateId;
	
	protected AbstractPossibleState(String stateId) {
		this.stateId = stateId;
	}
	
	@Override
	public String getStateId() {
		return this.stateId;
	}

}
