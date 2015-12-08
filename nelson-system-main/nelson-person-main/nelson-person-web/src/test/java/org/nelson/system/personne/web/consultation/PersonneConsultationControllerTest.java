package org.nelson.system.personne.web.consultation;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.personne.api.PersonneService;
import org.nelson.system.personne.web.exception.MissingIdException;
import org.nelson.system.personne.web.exception.UnknownPersonneException;

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
		when(personneService.findById(fakeId)).thenReturn(expected);
		
		underTest.init(fakeId);
		verify(personneService).findById(fakeId);
		Personne actual = underTest.getPersonne();
		Assert.assertSame(expected, actual);
	}
	
	@Test
	public void testInitWhenIdNotExists() {
		Long fakeId = 1L;
		when(personneService.findById(fakeId)).thenReturn(null);
		
		try {
			underTest.init(fakeId);
			Assert.fail("L'absence de l'entité devrait produire une erreur");
		} catch (UnknownPersonneException e) {
			verify(personneService).findById(fakeId);
			Assert.assertEquals(fakeId, e.getId());
		}
	}
	
	@Test
	public void testInitWhenIdNull() {
		try {
			underTest.init(null);
			Assert.fail("L'absence d'id devrait produire une erreur");
		} catch (MissingIdException e) {
			verifyZeroInteractions(personneService);
		}
	}
}
