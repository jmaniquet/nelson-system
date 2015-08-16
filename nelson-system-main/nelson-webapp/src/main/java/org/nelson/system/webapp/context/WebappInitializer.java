package org.nelson.system.webapp.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.nelson.system.core.api.context.CoreApiContext;
import org.nelson.system.core.web.context.CoreWebContext;
import org.nelson.system.infrastructure.environment.context.InfrastructureEnvironmentContext;
import org.nelson.system.personne.web.context.PersonneWebContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebappInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { InfrastructureEnvironmentContext.class,
								CoreApiContext.class,
								CoreWebContext.class,
								PersonneWebContext.class,
								WebappContext.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/app/*" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		// Use JSF view templates saved as *.xhtml, for use with Facelets
		servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		servletContext.setInitParameter("javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL", "true");

		servletContext.setInitParameter("primefaces.THEME", "#{userPreferences.theme.themeName}");
		
		// Let the DispatcherServlet be registered
		super.onStartup(servletContext);
	}
}
