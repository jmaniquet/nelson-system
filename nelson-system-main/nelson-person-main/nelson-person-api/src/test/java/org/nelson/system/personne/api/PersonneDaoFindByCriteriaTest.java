package org.nelson.system.personne.api;

import static org.nelson.system.personne.api.PersonneDaoFindByCriteriaDataConstants.BIRTHDATE_ENDCLAUSE;
import static org.nelson.system.personne.api.PersonneDaoFindByCriteriaDataConstants.BIRTHDATE_STARTCLAUSE;
import static org.nelson.system.personne.api.PersonneDaoFindByCriteriaDataConstants.GIVENNAME_CRITERIA;
import static org.nelson.system.personne.api.PersonneDaoFindByCriteriaDataConstants.NAME_CRITERIA;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nelson.system.core.db.context.CoreDbContext;
import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.personne.api.context.PersonneApiContext;
import org.nelson.system.tools.test.core.ToolsTestCoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ToolsTestCoreConfig.class,
		CoreDbContext.class,
		PersonneApiContext.class}
)
@TestExecutionListeners(listeners = DbUnitTestExecutionListener.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("classpath:/personne/findbycriteria.xml")
public class PersonneDaoFindByCriteriaTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static final Logger logger = LoggerFactory.getLogger(PersonneDaoFindByCriteriaTest.class);
	
	@Autowired
	private PersonneDao underTest;
	
	@Test
	public void testNameClause() {
		PersonneRechercheCriteria criteria = new PersonneRechercheCriteria();
		criteria.setName(NAME_CRITERIA);
		
		List<Personne> personnes = underTest.findByCriteria(criteria);
		Assert.assertFalse(personnes.isEmpty());
		
		for (Personne personne : personnes) {
			log(personne);
			String name = personne.getName();
			Assert.assertFalse(NAME_CRITERIA.equals(name));
			Assert.assertTrue(name.startsWith(NAME_CRITERIA));
		}
	}
	
	@Test
	public void testGivenNameClause() {
		PersonneRechercheCriteria criteria = new PersonneRechercheCriteria();
		criteria.setGivenName(GIVENNAME_CRITERIA);
		
		List<Personne> personnes = underTest.findByCriteria(criteria);
		Assert.assertFalse(personnes.isEmpty());
		
		for (Personne personne : personnes) {
			log(personne);
			String givenName = personne.getGivenName();
			Assert.assertFalse(GIVENNAME_CRITERIA.equals(givenName));
			Assert.assertTrue(givenName.startsWith(GIVENNAME_CRITERIA));
		}
	}
	
	@Test
	public void testBirthDateStartClause() {
		PersonneRechercheCriteria criteria = new PersonneRechercheCriteria();
		criteria.setBirthDateStart(BIRTHDATE_STARTCLAUSE);
		
		List<Personne> personnes = underTest.findByCriteria(criteria);
		Assert.assertFalse(personnes.isEmpty());
		
		boolean foundCriteriaBefore = false;
		boolean foundCriteriaEquals = false;
		for (Personne personne : personnes) {
			log(personne);
			DateTime birthDate = personne.getBirthDate();
			boolean isCriteriaBefore = BIRTHDATE_STARTCLAUSE.isBefore(birthDate);
			boolean isCriteriaEquals = BIRTHDATE_STARTCLAUSE.isEqual(birthDate);
			
			if (isCriteriaBefore) {
				foundCriteriaBefore = true;
			}
			if (isCriteriaEquals) {
				foundCriteriaEquals = true;
			}
			
			Assert.assertTrue(isCriteriaEquals || isCriteriaBefore);
		}
		
		Assert.assertTrue(foundCriteriaBefore);
		Assert.assertTrue(foundCriteriaEquals);
	}
	
	@Test
	public void testBirthDateEndClause() {
		PersonneRechercheCriteria criteria = new PersonneRechercheCriteria();
		criteria.setBirthDateEnd(BIRTHDATE_ENDCLAUSE);
		
		List<Personne> personnes = underTest.findByCriteria(criteria);
		Assert.assertFalse(personnes.isEmpty());
		
		for (Personne personne : personnes) {
			log(personne);
			DateTime birthDate = personne.getBirthDate();
			boolean isCriteriaAfter = BIRTHDATE_ENDCLAUSE.isAfter(birthDate);
			Assert.assertTrue(isCriteriaAfter);
		}
	}
	
	@Test
	public void testBirthDateStartAndEndClause() {
		PersonneRechercheCriteria criteria = new PersonneRechercheCriteria();
		criteria.setBirthDateStart(BIRTHDATE_STARTCLAUSE);
		criteria.setBirthDateEnd(BIRTHDATE_ENDCLAUSE);
		
		List<Personne> personnes = underTest.findByCriteria(criteria);
		Assert.assertFalse(personnes.isEmpty());
		
		boolean foundCriteriaBefore = false;
		boolean foundCriteriaEquals = false;
		
		for (Personne personne : personnes) {
			log(personne);
			DateTime birthDate = personne.getBirthDate();
			boolean isCriteriaBefore = BIRTHDATE_STARTCLAUSE.isBefore(birthDate);
			boolean isCriteriaEquals = BIRTHDATE_STARTCLAUSE.isEqual(birthDate);
			Assert.assertTrue(isCriteriaEquals || isCriteriaBefore);
			
			if (isCriteriaBefore) {
				foundCriteriaBefore = true;
			}
			if (isCriteriaEquals) {
				foundCriteriaEquals = true;
			}
			
			boolean isCriteriaAfter = BIRTHDATE_ENDCLAUSE.isAfter(birthDate);
			Assert.assertTrue(isCriteriaAfter);
		}
		
		Assert.assertTrue(foundCriteriaBefore);
		Assert.assertTrue(foundCriteriaEquals);
	}
	
	@Test
	public void testSyntaxAllCriteria() {
		PersonneRechercheCriteria criteria = new PersonneRechercheCriteria();
		criteria.setName(NAME_CRITERIA);
		criteria.setGivenName(GIVENNAME_CRITERIA);
		criteria.setBirthDateStart(BIRTHDATE_STARTCLAUSE);
		criteria.setBirthDateEnd(BIRTHDATE_ENDCLAUSE);
		
		underTest.findByCriteria(criteria);
	}
	
	private void log(Personne p) {
		logger.info("Personne - id = {}; name = {}; givenName = {}; birthDate = {}",
				p.getId(),
				p.getName(),
				p.getGivenName(),
				p.getBirthDate());
	}
}
