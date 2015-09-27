package org.nelson.system.core.db.personne.mapper;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nelson.system.core.db.CoreDbConfig;
import org.nelson.system.core.db.personne.domain.Personne;
import org.nelson.system.tools.test.core.ToolsTestCoreConfig;
import org.nelson.system.tools.test.core.random.RandomUtils;
import org.nelson.system.tools.test.core.user.User;
import org.nelson.system.tools.test.core.user.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ToolsTestCoreConfig.class,
		CoreDbConfig.class}
)
public class PersonneMapperTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private PersonneMapper underTest;
	
	@Test
	public void testInsertWithBirthDateNotNull() {
		String name = "bla";
		String givenName = "blu";
		DateTime birthDate = RandomUtils.randomDate();
		
		Personne personne = new Personne();
		personne.setName(name);
		personne.setGivenName(givenName);
		personne.setBirthDate(birthDate);
		
		underTest.insert(personne);
		
		Long generatedId = personne.getId();
		Assert.assertNotNull(generatedId);
		
		User actualUser = userUtils.findUserById(generatedId);
		
		Assert.assertNotNull(actualUser);
		Assert.assertEquals(generatedId, actualUser.getId());
		Assert.assertEquals(name, actualUser.getName());
		Assert.assertEquals(givenName, actualUser.getGivenName());
		Assert.assertEquals(birthDate, actualUser.getBirthDate());
	}
}
