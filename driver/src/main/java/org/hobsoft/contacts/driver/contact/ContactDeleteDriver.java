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
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.microbrowser.MicrodataItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Web UI driver for the delete contact page.
 */
@Component
public class ContactDeleteDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final ContactsViewDriver contactsDriver;
	
	private final ContactViewDriver contactViewDriver;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactDeleteDriver(DriverConfiguration config, ContactsViewDriver contactsDriver,
		ContactViewDriver contactViewDriver)
	{
		super(config, "/contact/\\d+/delete");
		
		this.contactsDriver = checkNotNull(contactsDriver, "contactsDriver");
		this.contactViewDriver = checkNotNull(contactViewDriver, "contactViewDriver");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public ContactDeleteDriver show(Contact contact)
	{
		return show(contact.getId());
	}
	
	public ContactDeleteDriver show(long id)
	{
		browser().get(url(String.format("/contact/%d/delete", id)));
		
		return this;
	}

	public Contact getContact()
	{
		checkVisible();
		
		MicrodataItem item = browser().getDocument().getItem("http://schema.org/Person");
		
		return ContactParser.parse(item);
	}

	public ContactsViewDriver delete()
	{
		checkVisible();
		
		browser().getDocument().getForm("contactDelete").submit();
		
		return contactsDriver;
	}

	public ContactViewDriver cancel()
	{
		checkVisible();

		browser().getDocument().getLink("cancel").follow();
		
		return contactViewDriver;
	}
}
