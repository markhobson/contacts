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
import org.hobsoft.contacts.client.contact.ContactViewDriver;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.junit.Test;

import static org.hobsoft.contacts.test.acceptance.auth.AcceptanceTestCredentials.USER;
import static org.hobsoft.contacts.test.acceptance.contact.ContactMatchers.contactEqualTo;
import static org.junit.Assert.assertThat;

/**
 * Acceptance test for the view contact page.
 */
public class ContactViewIT extends AbstractSecurePageIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void pageShowsContact()
	{
		Contact contact = api().signIn(USER)
			.create(new Contact("x"))
			.get();
		
		Contact actual = ui().signIn(USER)
			.contact("x")
			.get();
		
		Contact expected = new Contact("x");
		expected.setId(contact.getId());
		assertThat(actual, contactEqualTo(expected));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractSecurePageIT methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected ContactViewDriver show()
	{
		api().signIn(USER)
			.create(new Contact("x"));
		
		return ui().signIn(USER)
			.contact("x");
	}
}
