package org.nelson.system.personne.web.creation;

import org.nelson.system.core.db.personne.domain.Personne;

public interface PersonneCreationController {
	
	void init();

	void create();

	Personne getPersonne();
}