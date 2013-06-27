package org.hobsoft.contacts.server.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.hobsoft.contacts.server.dao.FakeContactDao;
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
		ResourceConfig application = new ResourceConfig();
		application.register(new ContactsResource(new FakeContactDao()));
		application.register(FreemarkerMvcFeature.class);
		application.property(FreemarkerProperties.TEMPLATES_BASE_PATH, "templates");
		return application;
	}
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void getReturnsHeader()
	{
		String actual = target("contacts").request().get(String.class);
		
		assertThat(actual, containsString("<h1>Contacts</h1>"));
	}
	
	@Test
	public void getReturnsContacts()
	{
		String actual = target("contacts").request().get(String.class);
		
		assertThat(actual, containsString("<li>A</li>"));
		assertThat(actual, containsString("<li>B</li>"));
		assertThat(actual, containsString("<li>C</li>"));
	}
}
