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
package org.hobsoft.contacts.test.acceptance.contact;

import org.hobsoft.contacts.client.contact.Contact;
import org.hobsoft.contacts.client.contact.ContactCreateDriver;
import org.hobsoft.contacts.client.contact.ContactViewDriver;
import org.hobsoft.contacts.client.contact.ContactsViewDriver;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hobsoft.contacts.test.acceptance.auth.AcceptanceTestCredentials.USER;
import static org.hobsoft.contacts.test.acceptance.contact.ContactMatchers.contactEqualTo;
import static org.junit.Assert.assertThat;

/**
 * Acceptance test for the view contacts page.
 */
public class ContactsViewIT extends AbstractSecurePageIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void pageWhenNoContactsShowsMessage()
	{
		ContactsViewDriver actual = ui().signIn(USER);
		
		assertThat(actual.getCaptionMessage(), is("No contacts found."));
	}
	
	@Test
	public void pageWhenContactsShowsContacts()
	{
		Contact contact1 = api().signIn(USER)
			.create(new Contact("x"))
			.get();
		Contact contact2 = api().signIn(USER)
			.create(new Contact("y"))
			.get();
		Contact contact3 = api().signIn(USER)
			.create(new Contact("z"))
			.get();
		
		ContactsViewDriver actual = ui().signIn(USER);
		
		assertThat(actual.getAll(), contains(
			contactEqualTo(contact1),
			contactEqualTo(contact2),
			contactEqualTo(contact3)
		));
	}
	
	@Test
	public void contactWhenClickedShowsContactView()
	{
		api().signIn(USER)
			.create(new Contact("x"));
		
		ContactViewDriver contactView = ui().signIn(USER)
			.contact("x");
		
		assertThat(contactView.isVisible(), is(true));
	}
	
	@Test
	public void createWhenClickedShowsContactCreate()
	{
		ContactCreateDriver contactCreate = ui().signIn(USER)
			.createForm();
		
		assertThat(contactCreate.isVisible(), is(true));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractSecurePageIT methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected ContactsViewDriver show()
	{
		return ui().signIn(USER);
	}
}
