package org.nelson.system.personne.web.exception;

import org.nelson.system.core.api.exception.NelsonException;

public class UnknownPersonneException extends NelsonException {
	private static final long serialVersionUID = -4003964122605069298L;

	private Long id;
	
	public UnknownPersonneException(Long id) {
		super("La personne d'identifiant " + id + " est inconnue");
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
