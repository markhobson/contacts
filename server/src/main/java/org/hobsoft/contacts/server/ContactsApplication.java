package org.hobsoft.contacts.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.hobsoft.contacts.server.resource.RootResource;

public class ContactsApplication extends ResourceConfig
{
	public ContactsApplication()
	{
		packages(RootResource.class.getPackage().getName());
		
		register(FreemarkerMvcFeature.class);
	}
}
