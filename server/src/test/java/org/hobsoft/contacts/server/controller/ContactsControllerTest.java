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
package org.hobsoft.contacts.server.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.server.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import static java.util.Arrays.asList;

import static org.junit.Assert.assertEquals;
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
		
		assertEquals("contactCreate", actual.getViewName());
	}
	
	@Test
	public void createCreatesContact()
	{
		Contact contact = new Contact();
		when(contactResourceAssembler.toResource(contact)).thenReturn(createContactResource(contact));
		
		controller.create(contact);

		verify(contactRepository).create(contact);
	}
	
	@Test
	public void createReturnsSeeOtherAndLocation() throws URISyntaxException
	{
		Contact contact = new Contact();
		when(contactResourceAssembler.toResource(contact)).thenReturn(new Resource<Contact>(contact, new Link("x")));
		
		ResponseEntity<Object> actual = controller.create(contact);
		
		assertEquals(HttpStatus.SEE_OTHER, actual.getStatusCode());
		assertEquals(new URI("x"), actual.getHeaders().getLocation());
	}

	@Test
	public void getAllAddsContactsToModel()
	{
		Contact contact = new Contact();
		List<Contact> contacts = asList(contact);
		when(contactRepository.getAll()).thenReturn(contacts);
		
		List<Resource<Contact>> resources = asList(new Resource<Contact>(contact));
		when(contactResourceAssembler.toResources(contacts)).thenReturn(resources);
		
		ModelAndView actual = controller.getAll();
		
		assertEquals(resources, actual.getModel().get("contacts"));
	}
	
	@Test
	public void getAllReturnsView()
	{
		ModelAndView actual = controller.getAll();
		
		assertEquals("contacts", actual.getViewName());
	}
	
	@Test
	public void getAddsContactToModel()
	{
		Contact contact = new Contact();
		when(contactRepository.get(1)).thenReturn(contact);
		
		Resource<Contact> resource = new Resource<Contact>(contact);
		when(contactResourceAssembler.toResource(contact)).thenReturn(resource);
		
		ModelAndView actual = controller.get(1);
		
		assertEquals(resource, actual.getModel().get("contact"));
	}

	@Test
	public void getReturnsView()
	{
		ModelAndView actual = controller.get(1);
		
		assertEquals("contact", actual.getViewName());
	}
	
	@Test
	public void deleteFormAddsContactToModel()
	{
		Contact contact = new Contact();
		when(contactRepository.get(1)).thenReturn(contact);
		
		Resource<Contact> resource = new Resource<Contact>(contact);
		when(contactResourceAssembler.toResource(contact)).thenReturn(resource);
		
		ModelAndView actual = controller.deleteForm(1);
		
		assertEquals(resource, actual.getModel().get("contact"));
	}

	@Test
	public void deleteFormReturnsView()
	{
		ModelAndView actual = controller.deleteForm(1);
		
		assertEquals("contactDelete", actual.getViewName());
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static Resource<Contact> createContactResource(Contact contact)
	{
		return new Resource<Contact>(contact, new Link("_self"));
	}
}
