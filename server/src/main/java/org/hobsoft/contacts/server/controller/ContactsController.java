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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hobsoft.contacts.domain.Contact;
import org.hobsoft.contacts.server.repository.ContactRepository;
import org.hobsoft.contacts.server.support.spring.hateoas.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Spring MVC controller for contacts.
 */
@Controller
public class ContactsController
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	private final ContactRepository contactRepository;
	
	private final ContactResourceAssembler contactResourceAssembler;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactsController(ContactRepository contactRepository, ContactResourceAssembler contactResourceAssembler)
	{
		this.contactRepository = checkNotNull(contactRepository, "contactRepository");
		this.contactResourceAssembler = checkNotNull(contactResourceAssembler, "contactResourceAssembler");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/contacts/create", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView createForm()
	{
		return new ModelAndView("contact/contactCreate");
	}
	
	@RequestMapping(value = "/contacts", method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> create(Contact contact)
	{
		contactRepository.create(contact);
		
		Link link = contactResourceAssembler.toResource(contact).getId();
		URI location = UriComponentsBuilder.fromUriString(link.getHref()).build().toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView getAll()
	{
		List<Contact> contacts = contactRepository.getAll();
		
		Map<String, Object> model = new HashMap<>();
		model.put("contacts", contactResourceAssembler.toResources(contacts));
		
		return new ModelAndView("contact/contactsView", model);
	}
	
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView get(@PathVariable long id)
	{
		Contact contact = contactRepository.get(id);
		
		Map<String, Object> model = new HashMap<>();
		model.put("contact", contactResourceAssembler.toResource(contact));
		
		return new ModelAndView("contact/contactView", model);
	}

	@RequestMapping(value = "/contact/{id}/delete", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView deleteForm(@PathVariable long id)
	{
		Contact contact = contactRepository.get(id);
		
		Map<String, Object> model = new HashMap<>();
		model.put("contact", contactResourceAssembler.toResource(contact));
		
		return new ModelAndView("contact/contactDelete", model);
	}
	
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable long id)
	{
		Contact contact = contactRepository.get(id);
		
		contactRepository.delete(contact);
		
		Link link = contactResourceAssembler.toResource(contact).getLink(Relation.COLLECTION.rel());
		URI location = UriComponentsBuilder.fromUriString(link.getHref()).build().toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}
}
