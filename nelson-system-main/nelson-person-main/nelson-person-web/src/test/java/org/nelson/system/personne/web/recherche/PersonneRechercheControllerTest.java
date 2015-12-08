package org.nelson.system.personne.web.recherche;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.personne.api.PersonneRechercheCriteria;
import org.nelson.system.personne.api.PersonneService;

public class PersonneRechercheControllerTest {

	@InjectMocks
	private PersonneRechercheControllerImpl underTest;
	
	@Mock
	private PersonneService personneService;
	
	@Before
	public void setUp() {
		underTest = new PersonneRechercheControllerImpl();
		initMocks(this);
	}
	
	@Test
	public void testInit() {
		underTest.init();
		
		PersonneRechercheCriteria criteria = underTest.getCriteria();
		Assert.assertNotNull(criteria);
		
		List<Personne> results = underTest.getResults();
		Assert.assertNotNull(results);
		Assert.assertTrue(results.isEmpty());
	}
	
	@Test
	public void testFind() {
		PersonneRechercheCriteria fakeParam = new PersonneRechercheCriteria();
		List<Personne> mockedResults = new ArrayList<>();
		mockedResults.add(new Personne());
		mockedResults.add(new Personne());
		mockedResults.add(new Personne());
		
		underTest.setCriteria(fakeParam);
		when(personneService.findByCriteria(fakeParam)).thenReturn(mockedResults);
		
		underTest.find();
		verify(personneService).findByCriteria(fakeParam);
		List<Personne> results = underTest.getResults();
		Assert.assertSame(mockedResults, results);
	}
}
