package org.hobsoft.contacts.server.support.mustache;

import org.glassfish.jersey.server.mvc.MvcProperties;

/**
 * Jersey MVC template processor properties for Mustache.java.
 */
public final class MustacheProperties
{
	// constants --------------------------------------------------------------
	
	public static final String TEMPLATES_BASE_PATH = MvcProperties.TEMPLATE_BASE_PATH + ".mustache";
	
	// constructors -----------------------------------------------------------
	
	private MustacheProperties()
	{
		throw new AssertionError();
	}
}
