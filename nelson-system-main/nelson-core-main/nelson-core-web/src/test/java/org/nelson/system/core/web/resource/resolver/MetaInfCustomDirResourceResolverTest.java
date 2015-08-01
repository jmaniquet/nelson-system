package org.nelson.system.core.web.resource.resolver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.net.URL;

import javax.faces.view.facelets.ResourceResolver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.nelson.system.core.web.resource.AbstractMetaInfCustomDirResourceTest;
import org.nelson.system.core.web.resource.resolver.MetaInfCustomDirResourceResolver;

public class MetaInfCustomDirResourceResolverTest extends AbstractMetaInfCustomDirResourceTest {

	@Mock
	private ResourceResolver parent;
	private MetaInfCustomDirResourceResolver underTest;
	
	@Before
	public void setUp() {
		initMocks(this);
		underTest = new MetaInfCustomDirResourceResolver(parent);
	}
	
	@Override
	public void testResourceFound() {
		when(parent.resolveUrl(TEST_PATH)).thenReturn(null);
		
		URL url = underTest.resolveUrl(TEST_PATH);
		verifyResultUrl(url);
		
		verify(parent).resolveUrl(TEST_PATH);
	}
	
	@Override
	public void testResourceNotFound() {
		String path = "fake";
		when(parent.resolveUrl(path)).thenReturn(null);
		
		URL url = underTest.resolveUrl(path);
		verify(parent).resolveUrl(path);
		Assert.assertNull(url);
	}
}
