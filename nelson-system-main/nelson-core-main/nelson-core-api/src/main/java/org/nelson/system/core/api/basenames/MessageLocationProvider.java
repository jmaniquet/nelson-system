package org.nelson.system.core.api.basenames;

/**
 * A module wanting to provide message files to the calling application should register one or several beans of this types.<br/>
 * "Basenames" is a list of resource locations, embedded into the module, typically properties file.<br/>
 * The calling application should detect all beans implementing this interface and use them to build
 * a MessageSource, preferably unique to the context.<br/>
 * @author jonathan
 *
 */
public interface MessageLocationProvider {

	public String[] getBaseNames();
}
