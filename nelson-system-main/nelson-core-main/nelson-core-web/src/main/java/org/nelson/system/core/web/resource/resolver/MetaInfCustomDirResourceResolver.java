package org.nelson.system.core.web.resource.resolver;

import java.net.URL;

import javax.faces.view.facelets.ResourceResolver;

public class MetaInfCustomDirResourceResolver extends ResourceResolver {

	private ResourceResolver parent;
	private String viewsBasePath;
	
	public MetaInfCustomDirResourceResolver(ResourceResolver parent) {
		this.parent = parent;
		this.viewsBasePath = "/META-INF/views"; // TODO: Make configureable?
	}

	@Override
	public URL resolveUrl(String path) {		
		URL url = parent.resolveUrl(path); // Resolves from WAR.
		
		if (url == null) {
			url = getClass().getResource(viewsBasePath + path); // Resolves from JAR.
		}
		/*if (url == null) {
			url = getClass().getResource("/META-INF/resources" + path); // Resolves from JAR.
		}*/

		return url;
	}
}
