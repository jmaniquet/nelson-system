package org.nelson.system.personne.api;

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
import org.nelson.system.core.db.personne.mapper.PersonneMapper;

public class PersonneServiceTest {

	@InjectMocks
	private PersonneService underTest;
	
	@Mock
	private PersonneMapper personneMapper;
	
	@Mock
	private PersonneDao personneDao;
	
	@Before
	public void setUp() {
		underTest = new PersonneServiceImpl();
		initMocks(this);
	}
	
	@Test
	public void testCreate() {
		Personne fakeParam = new Personne();
		underTest.create(fakeParam);
		verify(personneMapper).insert(fakeParam);
	}
	
	@Test
	public void testFindByCriteria() {
		PersonneRechercheCriteria fakeParam = new PersonneRechercheCriteria();
		List<Personne> l = new ArrayList<>();
		when(personneDao.findByCriteria(fakeParam)).thenReturn(l);
		
		List<Personne> result = underTest.findByCriteria(fakeParam);
		
		verify(personneDao).findByCriteria(fakeParam);
		Assert.assertSame(l, result);
	}
	
	@Test
	public void testFindByIdWhenExists() {
		Long fakeId = 1L;
		Personne expected = new Personne();
		when(personneMapper.selectByPrimaryKey(fakeId)).thenReturn(expected);
		
		Personne actual = underTest.findById(fakeId);
		
		verify(personneMapper).selectByPrimaryKey(fakeId);
		Assert.assertSame(expected, actual);
	}
	
	@Test
	public void testFindByIdWhenNotExists() {
		Long fakeId = 1L;
		when(personneMapper.selectByPrimaryKey(fakeId)).thenReturn(null);
		
		Personne actual = underTest.findById(fakeId);
		
		verify(personneMapper).selectByPrimaryKey(fakeId);
		Assert.assertNull(actual);
	}
	
	@Test
	public void testUpdate() {
		Personne fakeParam = new Personne();
		underTest.update(fakeParam);
		verify(personneMapper).updateByPrimaryKey(fakeParam);
	}
}
