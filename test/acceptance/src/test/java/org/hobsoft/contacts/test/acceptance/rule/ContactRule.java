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

import org.hobsoft.contacts.driver.ContactDeleteDriver;
import org.hobsoft.contacts.driver.event.ContactCollector;
import org.hobsoft.contacts.model.Contact;
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
	
	private final ContactDeleteDriver contactDeleteDriver;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	@Autowired
	public ContactRule(ContactCollector contactCollector, ContactDeleteDriver contactDeleteDriver)
	{
		this.contactCollector = checkNotNull(contactCollector, "contactCollector");
		this.contactDeleteDriver = checkNotNull(contactDeleteDriver, "contactDeleteDriver");
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
			contactDeleteDriver.show(contact)
				.delete();
		}
		
		contactCollector.clear();
	}
}
