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

import org.hobsoft.contacts.driver.contact.ContactViewDriver;
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hobsoft.contacts.test.acceptance.auth.AcceptanceTestCredentials.USER;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Acceptance test for the view contacts page.
 */
public class ContactsViewIT extends AbstractSecurePageIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void pageShowsContacts()
	{
		Contact contact1 = api().contacts()
			.create(new Contact("x"))
			.get();
		Contact contact2 = api().contacts()
			.create(new Contact("y"))
			.get();
		Contact contact3 = api().contacts()
			.create(new Contact("z"))
			.get();
		
		ContactsViewDriver actual = ui().signIn(USER);
		
		assertThat(actual.getAll(), contains(
			samePropertyValuesAs(contact1),
			samePropertyValuesAs(contact2),
			samePropertyValuesAs(contact3)
		));
	}
	
	@Test
	public void contactWhenClickedShowsContactView()
	{
		api().contacts()
			.create(new Contact("x"));
		
		ContactViewDriver contactView = ui().signIn(USER)
			.contact("x");
		
		assertTrue(contactView.isVisible());
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractSecurePageIT methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ContactsViewDriver show()
	{
		return ui().signIn(USER);
	}
}
