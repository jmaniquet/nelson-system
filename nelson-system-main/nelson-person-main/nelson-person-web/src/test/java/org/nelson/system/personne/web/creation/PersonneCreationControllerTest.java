package org.nelson.system.personne.web.creation;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.nelson.system.core.db.personne.domain.Person;
import org.nelson.system.personne.api.PersonneService;

public class PersonneCreationControllerTest {

	@InjectMocks
	private PersonneCreationControllerImpl underTest;
	
	@Mock
	private PersonneService personneService;
	
	@Before
	public void setUp() {
		underTest = new PersonneCreationControllerImpl();
		initMocks(this);
	}
	
	@Test
	public void testInit() {
		underTest.init();
		Person p = underTest.getPersonne();
		Assert.assertNotNull(p);
		Assert.assertNull(p.getId());
		Assert.assertNull(p.getName());
		Assert.assertNull(p.getGivenName());
		Assert.assertNull(p.getBirthDate());	
	}
	
	@Test
	public void testCreate() {
		Person fakeParam = new Person();
		underTest.setPersonne(fakeParam);
		
		underTest.create();
		Mockito.verify(personneService).create(fakeParam);
	}
}
