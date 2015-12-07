package org.nelson.system.personne.api;

import java.util.List;

import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.core.db.personne.mapper.PersonneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class PersonneServiceImpl implements PersonneService {
	
	@Autowired
	private PersonneMapper personneMapper;
	
	@Autowired
	private PersonneDao personneDao;
	
	@Override
	@Transactional
	public void create(Personne personne) {
		personneMapper.insert(personne);
	}

	@Override
	public List<Personne> findByCriteria(PersonneRechercheCriteria criteria) {
		return personneDao.findByCriteria(criteria);
	}

	@Override
	public Personne findById(Long id) {
		return personneMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public void update(Personne personne) {
		personneMapper.updateByPrimaryKey(personne);
	}
}
