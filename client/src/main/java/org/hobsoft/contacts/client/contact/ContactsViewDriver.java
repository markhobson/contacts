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
package org.hobsoft.contacts.client.contact;

import java.util.ArrayList;
import java.util.List;

import org.hobsoft.contacts.client.AbstractPageDriver;
import org.hobsoft.contacts.client.DriverConfiguration;
import org.hobsoft.contacts.client.model.Contact;
import org.hobsoft.microbrowser.MicrodataDocument;
import org.hobsoft.microbrowser.MicrodataItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hobsoft.contacts.client.MicrodataSchema.CAPTION;
import static org.hobsoft.contacts.client.MicrodataSchema.PERSON;

/**
 * Driver for the view contacts page.
 */
@Component
public class ContactsViewDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactsViewDriver(DriverConfiguration config, MicrodataDocument document)
	{
		super(config, document, "/contacts");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public String getCaptionMessage()
	{
		return document()
			.getItem(CAPTION)
			.getProperty("message")
			.getValue();
	}
	
	public List<Contact> getAll()
	{
		checkVisible();
		
		List<Contact> contacts = new ArrayList<>();
		
		for (MicrodataItem item : document().getItems(PERSON))
		{
			contacts.add(ContactParser.parse(item));
		}
		
		return contacts;
	}

	public ContactViewDriver contact(String name)
	{
		checkVisible();
		
		for (MicrodataItem item : document().getItems(PERSON))
		{
			Contact contact = ContactParser.parse(item);
			
			if (name.equals(contact.getName()))
			{
				MicrodataDocument document = item.getLink("item").follow();
				
				return new ContactViewDriver(getConfiguration(), document);
			}
		}
		
		throw new ContactNotFoundException(name);
	}
	
	public ContactViewDriver create(Contact contact)
	{
		return createForm().set(contact)
			.create();
	}

	public ContactCreateDriver createForm()
	{
		checkVisible();
		
		MicrodataDocument document = document().getLink("create-form").follow();
		
		return new ContactCreateDriver(getConfiguration(), document);
	}
}
