package org.hobsoft.contacts.server;

import org.glassfish.jersey.server.ResourceConfig;

public class ContactsApplication extends ResourceConfig
{
	public ContactsApplication()
	{
		packages("org.hobsoft.contacts.server");
	}
}
