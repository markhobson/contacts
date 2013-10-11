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
package org.hobsoft.contacts.server.repository;

import java.util.Arrays;
import java.util.List;

import org.hobsoft.contacts.model.Contact;
import org.springframework.stereotype.Repository;

/**
 * In-memory contact repository implementation.
 */
@Repository
public class FakeContactRepository implements ContactRepository
{
	// ----------------------------------------------------------------------------------------------------------------
	// ContactRepository methods
	// ----------------------------------------------------------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Contact> getAll()
	{
		return Arrays.asList(
			createContact("A"),
			createContact("B"),
			createContact("C")
		);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private Contact createContact(String name)
	{
		Contact contact = new Contact();
		contact.setName(name);
		return contact;
	}
}
