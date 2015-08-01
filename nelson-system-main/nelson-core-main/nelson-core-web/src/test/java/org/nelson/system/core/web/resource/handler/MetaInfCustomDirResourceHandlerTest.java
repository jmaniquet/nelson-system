package org.nelson.system.core.web.resource.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.net.URL;

import javax.faces.application.ResourceHandler;
import javax.faces.application.ViewResource;
import javax.faces.context.FacesContext;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mock;
import org.nelson.system.core.web.resource.AbstractMetaInfCustomDirResourceTest;
import org.nelson.system.core.web.resource.handler.MetaInfCustomDirResourceHandler;

public class MetaInfCustomDirResourceHandlerTest extends AbstractMetaInfCustomDirResourceTest {

	@Mock
	private ResourceHandler parent;
	
	@Mock
	private FacesContext facesContext;
	
	@Mock
	private ViewResource viewResourceMocked; 
	
	private MetaInfCustomDirResourceHandler underTest;
	
	@Before
	public void setUp() {
		initMocks(this);
		underTest = new MetaInfCustomDirResourceHandler(parent);
	}
	
	@Override
	public void testResourceFound() {
		when(parent.createViewResource(facesContext, TEST_PATH)).thenReturn(null);
		
		ViewResource vr = underTest.createViewResource(facesContext, TEST_PATH);
		Assert.assertNotNull(vr);
		
		URL vrAsUrl = vr.getURL();
		verifyResultUrl(vrAsUrl);
		
		verify(parent).createViewResource(facesContext, TEST_PATH);
	}

	@Override
	public void testResourceNotFound() {
		String path = "fake";
		
		when(parent.createViewResource(facesContext, path)).thenReturn(null);
		
		ViewResource vr = underTest.createViewResource(facesContext, path);
		Assert.assertNull(vr);
		
		verify(parent).createViewResource(facesContext, path);
	}

}
