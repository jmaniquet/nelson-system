package org.nelson.system.core.api.mybatis.handler;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nelson.system.core.api.context.CoreApiContext;
import org.nelson.system.tools.test.core.context.ToolsTestContext;
import org.nelson.system.tools.test.core.random.RandomUtils;
import org.nelson.system.tools.test.core.user.User;
import org.nelson.system.tools.test.core.user.UserBuilder;
import org.nelson.system.tools.test.core.user.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ToolsTestContext.class,
		CoreApiContext.class}
)
public class JodaTimeHandlerInsertTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private UserMapper underTest;
	
	private Long id = RandomUtils.randomId();
	private String name = "fakeName";
	private String givenName = "fakeGivenName";
	
	@Test
	public void testInsertWithBirthDateNotNull() {
		DateTime birthDate = RandomUtils.randomDate();
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).birthDate(birthDate).build();
		
		underTest.insert(expectedUser);
		
		User actualUser = userUtils.findUserById(id);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testInsertWithBirthDateNull() {
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).build();
		underTest.insert(expectedUser);
		
		User actualUser = userUtils.findUserById(id);
		userUtils.assertEquals(expectedUser, actualUser);
	}
}
