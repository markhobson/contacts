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
package org.hobsoft.contacts.driver.contact;

import java.util.ArrayList;
import java.util.List;

import org.hobsoft.contacts.driver.AbstractPageDriver;
import org.hobsoft.contacts.driver.DriverConfiguration;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.microbrowser.MicrodataItem;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Web UI driver for the view contacts page.
 */
@Component
public class ContactsViewDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final ContactCreateDriver contactCreate;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactsViewDriver(DriverConfiguration config, ContactCreateDriver contactCreate)
	{
		super(config, "/contacts");
		
		this.contactCreate = checkNotNull(contactCreate, "contactCreate");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public ContactsViewDriver show()
	{
		document().get(url("/contacts"));
		
		return this;
	}

	public ContactCreateDriver createForm()
	{
		checkVisible();
		
		document().getLink("create").follow();
		
		return contactCreate;
	}
	
	public List<Contact> getAll()
	{
		checkVisible();
		
		List<Contact> contacts = new ArrayList<>();
		
		for (MicrodataItem item : document().getItems("http://schema.org/Person"))
		{
			contacts.add(ContactParser.parse(item));
		}
		
		return contacts;
	}

	public void contact(String name)
	{
		checkVisible();
		
		for (MicrodataItem item : document().getItems("http://schema.org/Person"))
		{
			Contact contact = ContactParser.parse(item);
			
			if (name.equals(contact.getName()))
			{
				item.getProperty("url").unwrap(WebElement.class).click();
				return;
			}
		}
		
		throw new IllegalArgumentException("Cannot find contact: " + name);
	}
}
