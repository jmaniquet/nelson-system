package org.nelson.system.personne.web.consultation;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Ignore;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nelson.system.personne.api.PersonneService;

@Ignore
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
}
