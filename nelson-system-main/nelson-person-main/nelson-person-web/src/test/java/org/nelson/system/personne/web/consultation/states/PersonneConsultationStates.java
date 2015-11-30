package org.nelson.system.personne.web.consultation.states;

import org.nelson.system.tools.test.web.flow.states.CloseState;
import org.nelson.system.tools.test.web.flow.states.ConsultationState;

public class PersonneConsultationStates {
	
	private PersonneConsultationStates() {}
	
	public static final ConsultationState consultation = new ConsultationState();
	public static final ErrorState error = new ErrorState();
	public static final CloseState close = new CloseState();
}
