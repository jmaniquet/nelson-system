package org.nelson.system.personne.web.recherche;

import java.util.List;

import org.nelson.system.core.db.personne.domain.Personne;

public interface PersonneRechercheController {

	void init();

	void find();
	
	List<Personne> getResults();
}
