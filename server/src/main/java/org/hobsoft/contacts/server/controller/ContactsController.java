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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.server.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		return new ModelAndView("contactCreate");
	}
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView getAll()
	{
		List<Contact> contacts = contactRepository.getAll();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("contacts", contactResourceAssembler.toResources(contacts));
		
		return new ModelAndView("contacts", model);
	}
	
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView get(@PathVariable long id)
	{
		Contact contact = contactRepository.get(id);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("contact", contactResourceAssembler.toResource(contact));
		
		return new ModelAndView("contact", model);
	}
}
