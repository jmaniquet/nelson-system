package org.nelson.system.core.api.spring.jdbc.argsetter;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nelson.system.core.api.context.CoreApiContext;
import org.nelson.system.core.api.spring.jdbc.BeanConstants;
import org.nelson.system.tools.test.core.context.ToolsTestCoreContext;
import org.nelson.system.tools.test.core.random.RandomUtils;
import org.nelson.system.tools.test.core.user.User;
import org.nelson.system.tools.test.core.user.UserBuilder;
import org.nelson.system.tools.test.core.user.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ToolsTestCoreContext.class,
		CoreApiContext.class}
)
public class JodaTimeJdbcTemplateInsertTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String SQL_INSERT_ALL_FIELDS = "INSERT INTO USERS (ID, NAME, GIVEN_NAME, BIRTH_DATE) VALUES (?, ?, ?, ?)";
	private static final String SQL_INSERT_WITH_DEFAULT_BIRTHDATE = "INSERT INTO USERS (ID, NAME, GIVEN_NAME) VALUES (?, ?, ?)";
	
	@Autowired
	@Qualifier(BeanConstants.CUSTOM_JDBCTEMPLATE)
	private JdbcTemplate underTest;
	
	@Autowired
	private UserUtils userUtils;
	
	private long id = RandomUtils.randomId();
	private String name = "fakeName";
	private String givenName = "fakeGivenName";
	private DateTime birthDate = RandomUtils.randomDate();
	
	@Test
	public void testInsert() {
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).birthDate(birthDate).build();
		underTest.update(SQL_INSERT_ALL_FIELDS, id, name, givenName, birthDate);
		
		User actualUser = userUtils.findUserById(id);
		Assert.assertNotNull(actualUser.getBirthDate());
		Assert.assertEquals(birthDate, actualUser.getBirthDate());
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testInsertWithBirthDateNullDefault() {
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).birthDate(null).build();
		underTest.update(SQL_INSERT_WITH_DEFAULT_BIRTHDATE, id, name, givenName);
		
		User actualUser = userUtils.findUserById(id);
		Assert.assertNull(actualUser.getBirthDate());
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testInsertWithBirthDateNullParam() {
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).birthDate(null).build();
		underTest.update(SQL_INSERT_ALL_FIELDS, id, name, givenName, null);
		
		User actualUser = userUtils.findUserById(id);
		Assert.assertNull(actualUser.getBirthDate());
		userUtils.assertEquals(expectedUser, actualUser);
	}
}
