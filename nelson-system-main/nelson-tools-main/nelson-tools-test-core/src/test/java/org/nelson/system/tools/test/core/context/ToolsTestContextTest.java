package org.nelson.system.tools.test.core.context;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nelson.system.tools.test.core.context.ToolsTestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ToolsTestContext.class)
public class ToolsTestContextTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ToolsTestContext toolsTestContext;
	
	@Test
	public void testDataSourceNotNull() {
		Assert.assertNotNull(dataSource);
	}
	
	@Test
	public void testJdbcTemplateNotNull() {
		Assert.assertNotNull(jdbcTemplate);
	}
	
	@Test
	public void testToolsTestContextNotNull() {
		Assert.assertNotNull(toolsTestContext);
	}
}
