package org.nelson.system.personne.web.consultation;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.personne.api.PersonneService;

public class PersonneConsultationControllerTest {

	@InjectMocks
	private PersonneConsultationControllerImpl underTest;
	
	@Mock
	private PersonneService personneService;
	
	@Before
	public void setUp() {
		underTest = new PersonneConsultationControllerImpl();
		initMocks(this);
	}
	
	@Test
	public void testInit() {
		Long fakeId = 1L;
		Personne expected = new Personne();
		Mockito.when(personneService.findById(fakeId)).thenReturn(expected);
		
		underTest.init(fakeId);
		Mockito.verify(personneService).findById(fakeId);
		Personne actual = underTest.getPersonne();
		Assert.assertSame(expected, actual);
	}
	
	@Test
	public void testInitWhenIdNotExists() {
		Long fakeId = 1L;
		Mockito.when(personneService.findById(fakeId)).thenReturn(null);
		
		try {
			underTest.init(fakeId);
			Assert.fail("L'absence de l'entité devrait produire une erreur");
		} catch (UnknownPersonneException e) {
			Mockito.verify(personneService).findById(fakeId);
			Assert.assertEquals(fakeId, e.getId());
		}
	}
	
	@Test
	public void testInitWhenIdNull() {
		try {
			underTest.init(null);
			Assert.fail("L'absence d'id devrait produire une erreur");
		} catch (MissingIdException e) {
			Mockito.verifyZeroInteractions(personneService);
		}
	}
}
