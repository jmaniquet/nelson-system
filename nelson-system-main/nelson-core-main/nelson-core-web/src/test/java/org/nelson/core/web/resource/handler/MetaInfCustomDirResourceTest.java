package org.nelson.core.web.resource.handler;

import java.net.URL;

import javax.faces.application.ViewResource;

import org.junit.Assert;
import org.nelson.core.web.resource.AbstractMetaInfCustomDirResourceTest;
import org.nelson.core.web.resource.handler.MetaInfCustomDirResource;

public class MetaInfCustomDirResourceTest extends AbstractMetaInfCustomDirResourceTest {

	@Override
	public void testResourceFound() {
		
		ViewResource vr = new MetaInfCustomDirResource(TEST_CUSTOM_DIR, TEST_PATH);
		
		URL url = vr.getURL();
		verifyResultUrl(url);
	}
	
	@Override
	public void testResourceNotFound() {
		ViewResource vr = new MetaInfCustomDirResource("bla", "blu");
		
		URL url = vr.getURL();
		Assert.assertNull(url);
	}
}
