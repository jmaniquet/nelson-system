package org.nelson.system.personne.api;

import org.nelson.system.core.db.personne.domain.Person;
import org.nelson.system.core.db.personne.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class PersonneServiceImpl implements PersonneService {
	
	@Autowired
	private PersonMapper personneMapper;
	
	@Override
	@Transactional
	public void create(Person personne) {
		personneMapper.insert(personne);
	}
}
