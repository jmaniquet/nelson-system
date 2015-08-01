package org.nelson.system.core.api.mybatis.handler;

import static org.nelson.system.tools.test.core.constants.TestDataConstants.USER1_BIRTHDATE;
import static org.nelson.system.tools.test.core.constants.TestDataConstants.USER1_GIVENNAME;
import static org.nelson.system.tools.test.core.constants.TestDataConstants.USER1_ID;
import static org.nelson.system.tools.test.core.constants.TestDataConstants.USER1_NAME;
import static org.nelson.system.tools.test.core.constants.TestDataConstants.USER2_GIVENNAME;
import static org.nelson.system.tools.test.core.constants.TestDataConstants.USER2_ID;
import static org.nelson.system.tools.test.core.constants.TestDataConstants.USER2_NAME;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nelson.system.core.api.context.CoreApiContext;
import org.nelson.system.tools.test.core.context.ToolsTestContext;
import org.nelson.system.tools.test.core.user.User;
import org.nelson.system.tools.test.core.user.UserBuilder;
import org.nelson.system.tools.test.core.user.UserUtils;
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
		ToolsTestContext.class,
		CoreApiContext.class}
)
@TestExecutionListeners(listeners = DbUnitTestExecutionListener.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("classpath:/test-dataset.xml")
public class JodaTimeHandlerFindByIdTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private UserMapper underTest;
	
	@Test
	public void testFindByIdWithBirthDateNotNull() {
		User expectedUser = UserBuilder.builder().id(USER1_ID).name(USER1_NAME).givenName(USER1_GIVENNAME).birthDate(USER1_BIRTHDATE).build();
		User actualUser = underTest.findById(USER1_ID);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testFindByIdWithBirthDateNull() {
		User expectedUser = UserBuilder.builder().id(USER2_ID).name(USER2_NAME).givenName(USER2_GIVENNAME).birthDate(null).build();
		User actualUser = underTest.findById(USER2_ID);
		userUtils.assertEquals(expectedUser, actualUser);
	}
}
