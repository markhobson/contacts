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
package org.hobsoft.contacts.test.acceptance;

import org.hobsoft.contacts.driver.AbstractPageDriver;
import org.hobsoft.contacts.driver.ContactCreateDriver;
import org.hobsoft.contacts.driver.ContactViewDriver;
import org.hobsoft.contacts.driver.ContactsDriver;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Acceptance test for the contacts page.
 */
public class ContactsIT extends AbstractSecurePageIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private ContactCreateDriver contactCreate;
	
	@Autowired
	private ContactsDriver contacts;
	
	@Autowired
	private ContactViewDriver contactView;
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	@Authenticated
	public void pageShowsContacts()
	{
		contactCreate.show()
			.setContact(new Contact("x"))
			.create();
		contactCreate.show()
			.setContact(new Contact("y"))
			.create();
		contactCreate.show()
			.setContact(new Contact("z"))
			.create();
		
		contacts.show();
		
		assertThat(contacts.getContacts(), contains(
			samePropertyValuesAs(new Contact("x")),
			samePropertyValuesAs(new Contact("y")),
			samePropertyValuesAs(new Contact("z"))
		));
	}
	
	@Test
	@Authenticated
	public void contactWhenClickedShowsContact()
	{
		contactCreate.show()
			.setContact(new Contact("x"))
			.create();
		
		contacts.show()
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
		contacts.show();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractPageDriver driver()
	{
		return contacts;
	}
}
