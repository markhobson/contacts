package org.hobsoft.contacts.server;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerProperties;
import org.hobsoft.contacts.server.resource.ContactsResource;
import org.hobsoft.contacts.server.support.GuiceResourceConfig;

public class ContactsApplication extends GuiceResourceConfig
{
	// constructors -----------------------------------------------------------
	
	@Inject
	public ContactsApplication(ServiceLocator serviceLocator)
	{
		super(serviceLocator);
		
		packages(ContactsResource.class.getPackage().getName());
		
		register(FreemarkerMvcFeature.class);
		
		property(FreemarkerProperties.TEMPLATES_BASE_PATH, "templates");
		
		module(new ContactsModule());
	}
}
