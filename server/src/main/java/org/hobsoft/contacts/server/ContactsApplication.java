package org.hobsoft.contacts.server;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.hobsoft.contacts.server.resource.ContactsResource;
import org.hobsoft.contacts.server.support.guice.GuiceResourceConfig;
import org.hobsoft.contacts.server.support.mustache.MustacheMvcFeature;
import org.hobsoft.contacts.server.support.mustache.MustacheProperties;

/**
 * Main JAX-RS application.
 */
public class ContactsApplication extends GuiceResourceConfig
{
	// constructors -----------------------------------------------------------
	
	@Inject
	public ContactsApplication(ServiceLocator serviceLocator)
	{
		super(serviceLocator);
		
		packages(ContactsResource.class.getPackage().getName());
		
		register(MustacheMvcFeature.class);
		
		property(MustacheProperties.TEMPLATES_BASE_PATH, "templates");
		
		module(new ContactsModule());
	}
}
