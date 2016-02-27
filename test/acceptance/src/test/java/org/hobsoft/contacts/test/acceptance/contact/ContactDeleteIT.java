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
import org.hobsoft.contacts.client.contact.ContactDeleteDriver;
import org.hobsoft.contacts.client.contact.ContactNotFoundException;
import org.hobsoft.contacts.client.contact.ContactViewDriver;
import org.hobsoft.contacts.client.contact.ContactsViewDriver;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hobsoft.contacts.test.acceptance.auth.AcceptanceTestCredentials.USER;
import static org.hobsoft.contacts.test.acceptance.contact.ContactMatchers.contactEqualTo;
import static org.junit.Assert.assertThat;

/**
 * Acceptance test for the delete contact page.
 */
public class ContactDeleteIT extends AbstractSecurePageIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private ExpectedException thrown = ExpectedException.none();
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Rule
	public ExpectedException getThrown()
	{
		return thrown;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void pageShowsName()
	{
		api().signIn(USER)
			.create(new Contact("x"));
		
		Contact actual = ui().signIn(USER)
			.contact("x")
			.deleteForm()
			.get();
		
		assertThat(actual.getName(), is("x"));
	}
	
	@Test
	public void deleteDeletesContact()
	{
		api().signIn(USER)
			.create(new Contact("x"));
		
		ui().signIn(USER)
			.contact("x")
			.deleteForm()
			.delete();
		
		ContactsViewDriver contactsView = api().signIn(USER);
		thrown.expect(ContactNotFoundException.class);
		contactsView.contact("x");
	}
	
	@Test
	public void cancelShowsContactView()
	{
		Contact contact = api().signIn(USER)
			.create(new Contact("x"))
			.get();
		
		ContactViewDriver actual = ui().signIn(USER)
			.contact("x")
			.deleteForm()
			.cancel();
		
		assertThat(actual.get(), contactEqualTo(contact));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractSecurePageIT methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected ContactDeleteDriver show()
	{
		api().signIn(USER)
			.create(new Contact("x"));
		
		return ui().signIn(USER)
			.contact("x")
			.deleteForm();
	}
}
