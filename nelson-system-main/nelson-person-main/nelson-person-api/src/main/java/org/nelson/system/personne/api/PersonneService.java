package org.nelson.system.personne.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.nelson.system.core.db.personne.domain.Personne;

public interface PersonneService {

	void create(Personne personne);
	
	List<Personne> findByCriteria(PersonneRechercheCriteria criteria);
}
