package org.hobsoft.contacts.server.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.hobsoft.contacts.server.ContactsApplication;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ContactsResourceTest extends JerseyTest
{
	// JerseyTest methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Application configure()
	{
		return new ContactsApplication();
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void get()
	{
		String actual = target("contacts").request().get(String.class);
		
		assertThat(actual, containsString("<h1>Contacts</h1>"));
	}
}
