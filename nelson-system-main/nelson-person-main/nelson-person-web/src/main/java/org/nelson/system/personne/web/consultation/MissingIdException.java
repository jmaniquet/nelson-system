package org.nelson.system.personne.web.consultation;

public class MissingIdException extends RuntimeException {
	private static final long serialVersionUID = 5153959525584000846L;

	public MissingIdException() {
		super("L'identifiant de la personne n'est pas renseigné");
	}
}
