package org.nelson.system.core.web.resource;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractMetaInfCustomDirResourceTest {

	protected static final String META_INF_ROOT = "/META-INF";
	protected static final String TEST_CUSTOM_DIR = "/views";
	protected static final String TEST_PATH = "/fake-common/fake-footer.xhtml";
	
	@Test
	public abstract void testResourceFound();
	
	@Test
	public abstract void testResourceNotFound();
	
	protected void verifyResultUrl(URL url) {
		Assert.assertNotNull(url);
		
		String expectedResultString = META_INF_ROOT + TEST_CUSTOM_DIR + TEST_PATH;
		String urlAsStr = url.toString();
		Assert.assertTrue(urlAsStr.endsWith(expectedResultString));
	}
}
