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

import org.hobsoft.contacts.driver.AbstractPageDriver;
import org.hobsoft.contacts.driver.contact.ContactCreateDriver;
import org.hobsoft.contacts.driver.contact.ContactViewDriver;
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Acceptance test for the view contacts page.
 */
public class ContactsViewIT extends AbstractSecurePageIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private ContactCreateDriver contactCreate;
	
	@Autowired
	private ContactsViewDriver contactsView;
	
	@Autowired
	private ContactViewDriver contactView;
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	@Authenticated
	public void pageShowsContacts()
	{
		Contact contact1 = contactCreate.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		Contact contact2 = contactCreate.show()
			.setContact(new Contact("y"))
			.create()
			.getContact();
		Contact contact3 = contactCreate.show()
			.setContact(new Contact("z"))
			.create()
			.getContact();
		
		contactsView.show();
		
		assertThat(contactsView.getContacts(), contains(
			samePropertyValuesAs(contact1),
			samePropertyValuesAs(contact2),
			samePropertyValuesAs(contact3)
		));
	}
	
	@Test
	@Authenticated
	public void contactWhenClickedShowsContactView()
	{
		contactCreate.show()
			.setContact(new Contact("x"))
			.create();
		
		contactsView.show()
			.clickContact("x");
		
		assertTrue(contactView.isVisible());
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractSecurePageIT methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void show()
	{
		contactsView.show();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractPageDriver driver()
	{
		return contactsView;
	}
}
