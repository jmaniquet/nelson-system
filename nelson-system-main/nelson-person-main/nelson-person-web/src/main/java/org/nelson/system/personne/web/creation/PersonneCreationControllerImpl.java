package org.nelson.system.personne.web.creation;

import java.io.Serializable;

import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.personne.api.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("personneCreationController")
@Scope("flow")
public class PersonneCreationControllerImpl implements Serializable, PersonneCreationController {
	
	private static final long serialVersionUID = 2928207986702787757L;
	
	@Autowired
	private transient PersonneService personneService;
	
	private Personne personne;

	/* (non-Javadoc)
	 * @see fr.si2m.tooling.personne.creation.PersonneCreationController#init()
	 */
	@Override
	public void init() {
		personne = new Personne();
	}
	
	/* (non-Javadoc)
	 * @see fr.si2m.tooling.personne.creation.PersonneCreationController#create()
	 */
	@Override
	public void create() {
		personneService.create(personne);
	}
	
	public Personne getPersonne() {
		return personne;
	}
	/**
	 * For testing purposes only, hence the package visibility
	 * @param personne
	 */
	void setPersonne(Personne personne) {
		this.personne = personne;
	}
}
