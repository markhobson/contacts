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
package org.hobsoft.contacts.driver.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hobsoft.contacts.model.Contact;
import org.springframework.stereotype.Component;

/**
 * Contact listener that collects events.
 */
@Component
public class ContactCollector implements ContactListener
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final List<Contact> createdContacts;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public ContactCollector()
	{
		createdContacts = new ArrayList<>();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// ContactListener methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void contactCreated(Contact contact)
	{
		createdContacts.add(contact);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public List<Contact> getCreatedContacts()
	{
		return Collections.unmodifiableList(createdContacts);
	}

	public void clear()
	{
		createdContacts.clear();
	}
}
