package org.nelson.system.core.web.resource.handler;

import java.net.URL;

import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.faces.application.ViewResource;
import javax.faces.context.FacesContext;

public class MetaInfCustomDirResourceHandler extends ResourceHandlerWrapper {

	private ResourceHandler resourceHandler = null;
	private String viewsBasePath;
	
	public MetaInfCustomDirResourceHandler(ResourceHandler resourceHandler) {
		super();
		this.resourceHandler = resourceHandler;
		this.viewsBasePath = "/views"; // TODO: Make configureable?
	}
	
	@Override
	public ViewResource createViewResource(FacesContext context, String resourceName) {
		ViewResource faceletResource = resourceHandler.createViewResource(context, resourceName);
		if (faceletResource == null) {
			faceletResource = new MetaInfCustomDirResource(this.viewsBasePath, resourceName);
			URL schrodingerUrl = faceletResource.getURL();
			if (schrodingerUrl == null) {
				faceletResource = null;
			}
		}
		
		return faceletResource;
	}

	@Override
	public ResourceHandler getWrapped() {
		return resourceHandler;
	}
}
