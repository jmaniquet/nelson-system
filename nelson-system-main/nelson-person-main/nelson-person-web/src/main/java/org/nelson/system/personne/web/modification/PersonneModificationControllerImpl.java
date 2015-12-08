package org.nelson.system.personne.web.modification;

import java.io.Serializable;

import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.personne.api.PersonneService;
import org.nelson.system.personne.web.exception.MissingIdException;
import org.nelson.system.personne.web.exception.UnknownPersonneException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("personneModificationController")
@Scope("flow")
public class PersonneModificationControllerImpl implements Serializable, PersonneModificationController {
	
	private static final long serialVersionUID = -8794397470513468981L;
	
	@Autowired
	private transient PersonneService personneService;
	
	private Personne personne;

	@Override
	public void init(Long id) {
		if (id == null) {
			throw new MissingIdException();
		}
		
		personne = personneService.findById(id);
		
		if (personne == null) {
			throw new UnknownPersonneException(id);
		}
	}

	@Override
	public void update() {
		personneService.update(personne);
	}

	@Override
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
