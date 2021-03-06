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
package org.hobsoft.contacts.server.contact;

import org.hobsoft.contacts.domain.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code ContactResourceAssembler}.
 */
public class ContactResourceAssemblerTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private ContactResourceAssembler resourceAssembler;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
		
		resourceAssembler = new ContactResourceAssembler();
	}
	
	@After
	public void tearDown()
	{
		RequestContextHolder.resetRequestAttributes();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void toResourceReturnsResourceWithSelfLink()
	{
		Contact contact = new Contact();
		contact.setId(1L);
		
		Link actual = resourceAssembler.toResource(contact).getId();
		assertThat(actual, is(new Link("http://localhost/contact/1")));
	}

	@Test
	public void toResourceReturnsResourceWithCollectionLink()
	{
		Contact contact = new Contact();
		contact.setId(1L);
		
		Link actual = resourceAssembler.toResource(contact).getLink("collection");
		assertThat(actual, is(new Link("http://localhost/contacts", "collection")));
	}
	
	@Test
	public void toResourceReturnsResourceWithDeleteLink()
	{
		Contact contact = new Contact();
		contact.setId(1L);
		
		Link actual = resourceAssembler.toResource(contact).getLink("delete");
		assertThat(actual, is(new Link("http://localhost/contact/1/delete", "delete")));
	}
}
