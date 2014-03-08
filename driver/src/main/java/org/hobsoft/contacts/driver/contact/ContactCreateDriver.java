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

import org.hobsoft.contacts.driver.AbstractPageDriver;
import org.hobsoft.contacts.driver.DriverConfiguration;
import org.hobsoft.contacts.driver.event.ContactListener;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.microbrowser.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Web UI driver for the create contact page.
 */
@Component
public class ContactCreateDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final ContactListener contactListener;
	
	private final ContactViewDriver contactViewDriver;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactCreateDriver(DriverConfiguration config, ContactListener contactListener,
		ContactViewDriver contactViewDriver)
	{
		super(config, "/contacts/create");
		
		this.contactListener = checkNotNull(contactListener, "contactListener");
		this.contactViewDriver = checkNotNull(contactViewDriver, "contactViewDriver");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public ContactCreateDriver show()
	{
		document().get(url("/contacts/create"));
		
		return this;
	}

	public Contact getContact()
	{
		checkVisible();
		
		String name = getForm().getParameter("name");
		
		return new Contact(name);
	}

	public ContactCreateDriver setContact(Contact contact)
	{
		checkVisible();
		
		getForm().setParameter("name", contact.getName());
		
		return this;
	}

	public ContactViewDriver create()
	{
		checkVisible();
		
		getForm().submit();
		
		if (contactViewDriver.isVisible())
		{
			contactListener.contactCreated(contactViewDriver.getContact());
		}
		
		return contactViewDriver;
	}

	// TODO: return ContactsViewDriver
	public void cancel()
	{
		checkVisible();
		
		document().getLink("cancel").follow();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private Form getForm()
	{
		return document().getForm("contactCreate");
	}
}
