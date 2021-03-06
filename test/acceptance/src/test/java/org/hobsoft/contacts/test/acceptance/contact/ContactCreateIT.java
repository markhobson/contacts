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
import org.hobsoft.contacts.client.contact.ContactsViewDriver;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hobsoft.contacts.test.acceptance.auth.AcceptanceTestCredentials.USER;
import static org.hobsoft.contacts.test.acceptance.contact.ContactMatchers.contactEqualTo;
import static org.junit.Assert.assertThat;

/**
 * Acceptance test for the create contact page.
 */
public class ContactCreateIT extends AbstractSecurePageIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void pageShowsForm()
	{
		Contact actual = ui().signIn(USER)
			.createForm()
			.get();
		
		assertThat(actual, contactEqualTo(new Contact("")));
	}
	
	@Test
	public void submitCreatesContact()
	{
		Contact actual = ui().signIn(USER)
			.createForm()
			.set(new Contact("x"))
			.create()
			.get();
		
		Contact expected = new Contact("x");
		expected.setId(actual.getId());
		assertThat(actual, contactEqualTo(expected));
	}
	
	@Test
	public void cancelShowsContactsView()
	{
		ContactsViewDriver contactsView = ui().signIn(USER)
			.createForm()
			.cancel();
		
		assertThat(contactsView.isVisible(), is(true));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractSecurePageIT methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected ContactCreateDriver show()
	{
		return ui().signIn(USER)
			.createForm();
	}
}
