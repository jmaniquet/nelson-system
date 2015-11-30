package org.nelson.system.personne.web.consultation;

import org.nelson.system.core.db.personne.domain.Personne;

public interface PersonneConsultationController {

	void init(Long id);
	
	Personne getPersonne();
}
