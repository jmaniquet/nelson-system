package org.nelson.system.tools.test.user;

import static org.nelson.system.tools.test.constants.TestDataConstants.USER1_BIRTHDATE;
import static org.nelson.system.tools.test.constants.TestDataConstants.USER1_GIVENNAME;
import static org.nelson.system.tools.test.constants.TestDataConstants.USER1_ID;
import static org.nelson.system.tools.test.constants.TestDataConstants.USER1_NAME;
import static org.nelson.system.tools.test.constants.TestDataConstants.USER2_GIVENNAME;
import static org.nelson.system.tools.test.constants.TestDataConstants.USER2_ID;
import static org.nelson.system.tools.test.constants.TestDataConstants.USER2_NAME;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import org.nelson.system.tools.test.context.ToolsTestContext;
import org.nelson.system.tools.test.user.User;
import org.nelson.system.tools.test.user.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ToolsTestContext.class)
@TestExecutionListeners(listeners = DbUnitTestExecutionListener.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("classpath:/test-dataset.xml")
public class UserUtilsFindUserByIdTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserUtils underTest;
	
	@Test
	public void testUserRowMapperWhenBirthDateNotNull() {
		User u = underTest.findUserById(USER1_ID);
		Assert.assertEquals(USER1_ID, u.getId());
		Assert.assertEquals(USER1_NAME, u.getName());
		Assert.assertEquals(USER1_GIVENNAME, u.getGivenName());
		Assert.assertEquals(USER1_BIRTHDATE, u.getBirthDate());
	}
	
	@Test
	public void testUserRowMapperWhenBirthDateNull() {
		User u = underTest.findUserById(USER2_ID);
		Assert.assertEquals(USER2_ID, u.getId());
		Assert.assertEquals(USER2_NAME, u.getName());
		Assert.assertEquals(USER2_GIVENNAME, u.getGivenName());
		Assert.assertNull(u.getBirthDate());
	}
}
