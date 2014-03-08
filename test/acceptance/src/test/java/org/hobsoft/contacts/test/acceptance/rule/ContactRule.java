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
package org.hobsoft.contacts.test.acceptance.rule;

import org.hobsoft.contacts.driver.ApplicationDriver;
import org.hobsoft.contacts.driver.contact.ContactViewDriver;
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.contacts.driver.event.ContactCollector;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.config.UI;
import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * JUnit rule to delete created contacts on tear down. 
 */
@Component
public class ContactRule extends ExternalResource
{
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private final ContactCollector contactCollector;
	
	private final ApplicationDriver api;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	// TODO: use API when contact() works under jsoup
	@Autowired
	public ContactRule(ContactCollector contactCollector, @UI ApplicationDriver api)
	{
		this.contactCollector = checkNotNull(contactCollector, "contactCollector");
		this.api = checkNotNull(api, "api");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// ExternalResource methods
	// ----------------------------------------------------------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void after()
	{
		for (Contact contact : contactCollector.getCreatedContacts())
		{
			deleteContactQuietly(contact);
		}
		
		contactCollector.clear();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private void deleteContactQuietly(Contact contact)
	{
		ContactsViewDriver contactsView = api.contacts()
			.show();
		
		ContactViewDriver contactView;
		try
		{
			contactView = contactsView.contact(contact.getName());
		}
		catch (IllegalArgumentException exception)
		{
			// ignore unknown contact
			return;
		}
		
		contactView.deleteForm()
			.delete();
	}
}
