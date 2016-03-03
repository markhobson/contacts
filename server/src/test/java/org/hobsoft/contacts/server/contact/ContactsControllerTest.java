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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.hobsoft.contacts.domain.Contact;
import org.hobsoft.contacts.domain.ContactRepository;
import org.hobsoft.contacts.server.Relation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import static java.util.Arrays.asList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests {@code ContactsController}.
 */
public class ContactsControllerTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private ContactRepository contactRepository;
	
	private ContactResourceAssembler contactResourceAssembler;
	
	private ContactsController controller;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Before
	public void setUp()
	{
		contactRepository = mock(ContactRepository.class);
		contactResourceAssembler = mock(ContactResourceAssembler.class);
		controller = new ContactsController(contactRepository, contactResourceAssembler);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullContactRepository()
	{
		new ContactsController(null, contactResourceAssembler);
	}

	@Test(expected = NullPointerException.class)
	public void constructorWithNullContactResourceAssembler()
	{
		new ContactsController(contactRepository, null);
	}

	@Test
	public void createFormReturnsView()
	{
		ModelAndView actual = controller.createForm();
		
		assertThat(actual.getViewName(), is("contact/contactCreate"));
	}
	
	@Test
	public void createCreatesContact()
	{
		Contact contact = new Contact();
		ContactResource resource = new ContactResource(contact, createLink(Relation.SELF));
		when(contactResourceAssembler.toResource(contact)).thenReturn(resource);
		
		controller.create(contact);

		verify(contactRepository).create(contact);
	}
	
	@Test
	public void createReturnsSeeOtherAndLocation() throws URISyntaxException
	{
		Contact contact = new Contact();
		ContactResource resource = new ContactResource(contact, new Link("x"));
		when(contactResourceAssembler.toResource(contact)).thenReturn(resource);
		
		ResponseEntity<Object> actual = controller.create(contact);
		
		assertThat(actual.getStatusCode(), is(HttpStatus.SEE_OTHER));
		assertThat(actual.getHeaders().getLocation(), is(new URI("x")));
	}

	@Test
	public void getAllAddsContactsToModel()
	{
		Contact contact = new Contact();
		List<Contact> contacts = asList(contact);
		when(contactRepository.getAll()).thenReturn(contacts);
		
		List<ContactResource> resources = asList(new ContactResource(contact));
		when(contactResourceAssembler.toResources(contacts)).thenReturn(resources);
		
		ModelAndView actual = controller.getAll();
		
		assertThat(actual.getModel().get("contacts"), is(resources));
	}
	
	@Test
	public void getAllReturnsView()
	{
		ModelAndView actual = controller.getAll();
		
		assertThat(actual.getViewName(), is("contact/contactsView"));
	}
	
	@Test
	public void getAddsContactToModel()
	{
		Contact contact = new Contact();
		when(contactRepository.get(1)).thenReturn(contact);
		
		ContactResource resource = new ContactResource(contact);
		when(contactResourceAssembler.toResource(contact)).thenReturn(resource);
		
		ModelAndView actual = controller.get(1);
		
		assertThat(actual.getModel().get("contact"), is(resource));
	}

	@Test
	public void getReturnsView()
	{
		ModelAndView actual = controller.get(1);
		
		assertThat(actual.getViewName(), is("contact/contactView"));
	}
	
	@Test
	public void deleteFormAddsContactToModel()
	{
		Contact contact = new Contact();
		when(contactRepository.get(1)).thenReturn(contact);
		
		ContactResource resource = new ContactResource(contact);
		when(contactResourceAssembler.toResource(contact)).thenReturn(resource);
		
		ModelAndView actual = controller.deleteForm(1);
		
		assertThat(actual.getModel().get("contact"), is(resource));
	}

	@Test
	public void deleteFormReturnsView()
	{
		ModelAndView actual = controller.deleteForm(1);
		
		assertThat(actual.getViewName(), is("contact/contactDelete"));
	}
	
	@Test
	public void deleteDeletesContact()
	{
		Contact contact = new Contact();
		when(contactRepository.get(1)).thenReturn(contact);
		
		ContactResource resource = new ContactResource(contact, createLink(Relation.COLLECTION));
		when(contactResourceAssembler.toResource(contact)).thenReturn(resource);

		controller.delete(1);

		verify(contactRepository).delete(contact);
	}

	@Test
	public void deleteReturnsSeeOtherAndLocation() throws URISyntaxException
	{
		Contact contact = new Contact();
		when(contactRepository.get(1)).thenReturn(contact);
		
		ContactResource resource = new ContactResource(contact, new Link("x", Relation.COLLECTION.rel()));
		when(contactResourceAssembler.toResource(contact)).thenReturn(resource);
		
		ResponseEntity<Object> actual = controller.delete(1);
		
		assertThat(actual.getStatusCode(), is(HttpStatus.SEE_OTHER));
		assertThat(actual.getHeaders().getLocation(), is(new URI("x")));
	}

	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static Link createLink(Relation relation)
	{
		return new Link("_href", relation.rel());
	}
}
