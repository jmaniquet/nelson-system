package org.nelson.system.core.api.spring.jdbc.argsetter;

import static org.nelson.system.tools.test.constants.TestDataConstants.USER1_BIRTHDATE;
import static org.nelson.system.tools.test.constants.TestDataConstants.USER1_ID;
import static org.nelson.system.tools.test.constants.TestDataConstants.USER2_ID;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nelson.system.core.api.context.CoreApiContext;
import org.nelson.system.core.api.spring.jdbc.BeanConstants;
import org.nelson.system.tools.test.context.ToolsTestContext;
import org.nelson.system.tools.test.random.RandomUtils;
import org.nelson.system.tools.test.user.User;
import org.nelson.system.tools.test.user.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class JodaTimeJdbcTemplateUpdateTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String SQL_UPDATE_BIRTHDATE_BY_ID = "UPDATE USERS SET BIRTH_DATE = ? WHERE ID = ?";
	
	@Autowired
	@Qualifier(BeanConstants.CUSTOM_JDBCTEMPLATE)
	private JdbcTemplate underTest;
	
	@Autowired
	private UserUtils userUtils;
	
	@Test
	public void testUpdateBirthDate() {
		checkBirthDate(USER1_ID, USER1_BIRTHDATE);
		DateTime newDate = RandomUtils.randomDate();
		underTest.update(SQL_UPDATE_BIRTHDATE_BY_ID, newDate, USER1_ID);
		checkBirthDate(USER1_ID, newDate);
	}
	
	@Test
	public void testUpdateBirthDateNotNullToNull() {
		checkBirthDate(USER1_ID, USER1_BIRTHDATE);
		underTest.update(SQL_UPDATE_BIRTHDATE_BY_ID, null, USER1_ID);
		checkBirthDate(USER1_ID, null);
	}
	
	@Test
	public void testUpdateBirthDateNullToNotNull() {
		checkBirthDate(USER2_ID, null);
		DateTime newDate = RandomUtils.randomDate();
		underTest.update(SQL_UPDATE_BIRTHDATE_BY_ID, newDate, USER2_ID);
		checkBirthDate(USER2_ID, newDate);
	}
	
	private void checkBirthDate(Long userId, DateTime expectedBirthDate) {
		User u = userUtils.findUserById(userId);
		DateTime actual = u.getBirthDate();
		
		if (expectedBirthDate == null) {
			Assert.assertNull(actual);
		} else {
			Assert.assertEquals(expectedBirthDate, actual);
		}
		
	}
}
