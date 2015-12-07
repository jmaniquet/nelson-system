package org.nelson.system.personne.web.consultation;

import java.io.Serializable;

import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.personne.api.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("personneConsultationController")
@Scope("flow")
public class PersonneConsultationControllerImpl implements Serializable, PersonneConsultationController {
	
	private static final long serialVersionUID = 4806022820603783553L;
	
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
	public Personne getPersonne() {
		return personne;
	}
}
