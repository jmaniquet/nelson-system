package org.nelson.system.personne.web.recherche;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.personne.api.PersonneRechercheCriteria;
import org.nelson.system.personne.api.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("personneRechercheController")
@Scope("flow")
public class PersonneRechercheControllerImpl implements Serializable, PersonneRechercheController {
	
	private static final long serialVersionUID = 3508967722934054795L;

	@Autowired
	private transient PersonneService personneService;
	
	private PersonneRechercheCriteria criteria;
	private List<Personne> results;
	
	/*
	 * (non-Javadoc)
	 * @see org.nelson.system.personne.web.recherche.PersonneRechercheController#init()
	 */
	@Override
	public void init() {
		criteria = new PersonneRechercheCriteria();
		results = new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * @see org.nelson.system.personne.web.recherche.PersonneRechercheController#find()
	 */
	@Override
	public void find() {
		results = personneService.findByCriteria(criteria);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.nelson.system.personne.web.recherche.PersonneRechercheController#getResults()
	 */
	@Override
	public List<Personne> getResults() {
		return results;
	}

	public PersonneRechercheCriteria getCriteria() {
		return criteria;
	}
	/**
	 * For testing purposes only, hence the package visibility
	 * @param criteria
	 */
	public void setCriteria(PersonneRechercheCriteria criteria) {
		this.criteria = criteria;
	}
}
