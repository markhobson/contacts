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
import java.util.Map;

import org.hobsoft.contacts.server.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Spring MVC controller for contacts.
 */
@Controller
@RequestMapping("/contacts")
public class ContactsController
{
	// fields -----------------------------------------------------------------
	
	private final ContactRepository contactRepository;
	
	// constructors -----------------------------------------------------------
	
	@Autowired
	public ContactsController(ContactRepository contactRepository)
	{
		this.contactRepository = checkNotNull(contactRepository, "contactRepository");
	}
	
	// public methods ---------------------------------------------------------
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView get()
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("contacts", contactRepository.getAll());
		
		return new ModelAndView("index", model);
	}
}
