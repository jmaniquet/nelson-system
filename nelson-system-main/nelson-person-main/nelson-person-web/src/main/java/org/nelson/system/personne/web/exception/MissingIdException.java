package org.nelson.system.personne.web.exception;

import org.nelson.system.core.api.exception.NelsonException;

public class MissingIdException extends NelsonException {
	private static final long serialVersionUID = 5153959525584000846L;

	public MissingIdException() {
		super("L'identifiant de la personne n'est pas renseigné");
	}
}
