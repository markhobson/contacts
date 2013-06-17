package org.hobsoft.contacts.server;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactsResourceTest extends JerseyTest
{
	// JerseyTest methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Application configure()
	{
		return new ResourceConfig(ContactsResource.class);
	}
	
	@Test
	public void contacts()
	{
		String actual = target("contacts").request().get(String.class);
		
		assertEquals("<html><body><h1>Contacts</h1></body></html>", actual);
	}
}
