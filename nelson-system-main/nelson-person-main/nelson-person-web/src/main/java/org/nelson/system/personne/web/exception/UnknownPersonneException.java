package org.nelson.system.personne.web.exception;

public class UnknownPersonneException extends RuntimeException {
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
