package org.nelson.system.personne.web.modification;

import org.nelson.system.core.db.personne.domain.Personne;

public interface PersonneModificationController {

	void init(Long id);

	void update();

	Personne getPersonne();
}
