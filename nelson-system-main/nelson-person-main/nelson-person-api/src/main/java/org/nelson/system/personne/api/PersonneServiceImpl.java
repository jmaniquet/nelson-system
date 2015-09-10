package org.nelson.system.personne.api;

import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.core.db.personne.mapper.PersonneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class PersonneServiceImpl implements PersonneService {
	
	@Autowired
	private PersonneMapper personneMapper;
	
	@Override
	@Transactional
	public void create(Personne personne) {
		personneMapper.insert(personne);
	}
}
