package org.nelson.system.personne.web.creation;

import java.io.Serializable;

import org.nelson.system.core.db.personne.domain.Person;
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
	
	private Person personne;

	/* (non-Javadoc)
	 * @see fr.si2m.tooling.personne.creation.PersonneCreationController#init()
	 */
	@Override
	public void init() {
		personne = new Person();
	}
	
	/* (non-Javadoc)
	 * @see fr.si2m.tooling.personne.creation.PersonneCreationController#create()
	 */
	@Override
	public void create() {
		personneService.create(personne);
	}
	
	public Person getPersonne() {
		return personne;
	}
	/**
	 * For testing purposes only, hence the package visibility
	 * @param personne
	 */
	void setPersonne(Person personne) {
		this.personne = personne;
	}
}
