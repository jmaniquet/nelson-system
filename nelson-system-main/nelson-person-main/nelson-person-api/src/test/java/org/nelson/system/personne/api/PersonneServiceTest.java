package org.nelson.system.personne.api;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.core.db.personne.mapper.PersonneMapper;

public class PersonneServiceTest {

	@InjectMocks
	private PersonneService underTest;
	
	@Mock
	private PersonneMapper personneMapper;
	
	@Before
	public void setUp() {
		underTest = new PersonneServiceImpl();
		initMocks(this);
	}
	
	@Test
	public void testCreate() {
		Personne fakeParam = new Personne();
		underTest.create(fakeParam);
		Mockito.verify(personneMapper).insert(fakeParam);
	}
}
