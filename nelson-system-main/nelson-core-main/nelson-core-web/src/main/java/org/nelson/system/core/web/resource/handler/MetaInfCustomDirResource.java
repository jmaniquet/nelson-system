package org.nelson.system.core.web.resource.handler;

import java.net.URL;

import javax.faces.application.ViewResource;

public class MetaInfCustomDirResource extends ViewResource {

	private static final String META_INF = "/META-INF";
	private String metaInfCustomDir;
	private String resourcePath;
	
	public MetaInfCustomDirResource(String metaInfCustomDir, String metaInfPath) {
		super();
		this.metaInfCustomDir = metaInfCustomDir;
		this.resourcePath = metaInfPath;
	}

	@Override
	public URL getURL() {
		String metaInfPath = META_INF + metaInfCustomDir + resourcePath;
		URL url = getClass().getResource(metaInfPath); // Resolves from JAR.
		return url;
	}

}
