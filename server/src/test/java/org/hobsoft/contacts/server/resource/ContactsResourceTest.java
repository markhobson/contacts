/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.contacts.server.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.hobsoft.contacts.server.dao.FakeContactDao;
import org.hobsoft.contacts.server.support.mustache.MustacheMvcFeature;
import org.hobsoft.contacts.server.support.mustache.MustacheProperties;
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
		application.register(MustacheMvcFeature.class);
		application.property(MustacheProperties.TEMPLATES_BASE_PATH, "templates");
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
