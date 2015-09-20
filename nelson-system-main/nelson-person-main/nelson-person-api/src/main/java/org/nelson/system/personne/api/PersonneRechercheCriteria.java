package org.nelson.system.personne.api;

import java.io.Serializable;

import org.joda.time.DateTime;

public class PersonneRechercheCriteria implements Serializable {
	private static final long serialVersionUID = -9201868959807774713L;

	private String name;
	private String givenName;
	private DateTime birthDateStart;
	private DateTime birthDateEnd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public DateTime getBirthDateStart() {
		return birthDateStart;
	}
	public void setBirthDateStart(DateTime birthDateStart) {
		this.birthDateStart = birthDateStart;
	}
	public DateTime getBirthDateEnd() {
		return birthDateEnd;
	}
	public void setBirthDateEnd(DateTime birthDateEnd) {
		this.birthDateEnd = birthDateEnd;
	}
}
