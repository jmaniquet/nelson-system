package org.nelson.system.personne.web.consultation.states;

import org.nelson.system.core.web.flow.states.ClosedState;
import org.nelson.system.core.web.flow.states.ConsultationState;
import org.nelson.system.core.web.flow.states.ErrorState;

public class PersonneConsultationStates {
	
	private PersonneConsultationStates() {}
	
	public static final ConsultationState consultation = new ConsultationState();
	public static final ErrorState error = new ErrorState();
	public static final ClosedState closed = new ClosedState();
}
