package org.nelson.system.core.api.spring.jdbc.template;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nelson.system.core.api.context.CoreApiContext;
import org.nelson.system.core.api.spring.jdbc.BeanConstants;
import org.nelson.system.tools.test.core.context.ToolsTestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ToolsTestContext.class,
		CoreApiContext.class}
)
public class JodaTimeJdbcTemplateTypeClassTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	@Qualifier(BeanConstants.CUSTOM_JDBCTEMPLATE)
	private JdbcTemplate customJdbcTemplate;
	
	@Autowired
	@Qualifier(BeanConstants.CLASSIC_JDBCTEMPLATE)
	private JdbcTemplate classicJdbcTemplate;
	
	@Test
	public void testClassicClass() {
		Assert.assertNotNull(classicJdbcTemplate);
		Assert.assertFalse(classicJdbcTemplate instanceof JodaTimeJdbcTemplate);
	}
	
	@Test
	public void testCustomisationClass() {
		Assert.assertNotNull(customJdbcTemplate);
		Assert.assertTrue(customJdbcTemplate instanceof JodaTimeJdbcTemplate);
	}
}
