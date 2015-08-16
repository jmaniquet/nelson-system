package org.nelson.system.personne.api;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.nelson.system.core.db.personne.domain.Person;
import org.nelson.system.core.db.personne.mapper.PersonMapper;

public class PersonneServiceTest {

	@InjectMocks
	private PersonneService underTest;
	
	@Mock
	private PersonMapper personneMapper;
	
	@Before
	public void setUp() {
		underTest = new PersonneServiceImpl();
		initMocks(this);
	}
	
	@Test
	public void testCreate() {
		Person fakeParam = new Person();
		underTest.create(fakeParam);
		Mockito.verify(personneMapper).insert(fakeParam);
	}
}
