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
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Test;

import static org.hamcrest.Matchers.samePropertyValuesAs;
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
	@Authenticated
	public void pageShowsContact()
	{
		Contact contact = api().contacts()
			.createForm()
			.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		Contact actual = ui().contactView()
			.show(contact)
			.getContact();
		
		Contact expected = new Contact("x");
		expected.setId(contact.getId());
		assertThat(actual, samePropertyValuesAs(expected));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractSecurePageIT methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ContactViewDriver show()
	{
		Contact contact = api().contacts()
			.createForm()
			.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		return ui().contactView()
			.show(contact);
	}
}
