package org.hobsoft.contacts.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerProperties;
import org.hobsoft.contacts.server.resource.ContactsResource;

public class ContactsApplication extends ResourceConfig
{
	public ContactsApplication()
	{
		packages(ContactsResource.class.getPackage().getName());
		
		register(FreemarkerMvcFeature.class);
		
		property(FreemarkerProperties.TEMPLATES_BASE_PATH, "templates");
	}
}
