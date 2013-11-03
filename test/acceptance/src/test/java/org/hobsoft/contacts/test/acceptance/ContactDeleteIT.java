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
import org.hobsoft.contacts.driver.ContactDeleteDriver;
import org.hobsoft.contacts.driver.ContactDriver;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Acceptance test for the delete contact page.
 */
public class ContactDeleteIT extends AbstractSecurePageIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private ContactCreateDriver contactCreate;
	
	@Autowired
	private ContactDeleteDriver contactDelete;
	
	@Autowired
	private ContactDriver contact;
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	@Authenticated
	public void pageShowsName()
	{
		Contact contact = contactCreate.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		Contact actual = contactDelete.show(contact)
			.getContact();
		
		contactDelete.show(contact)
			.delete();
		
		assertThat(actual.getName(), is("x"));
	}
	
	@Ignore("Reinstate when we can assert contact not found")
	@Test
	@Authenticated
	public void deleteDeletesContact()
	{
		Contact contact = contactCreate.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		contactDelete.show(contact)
			.delete();
		
		this.contact.show(contact.getId());
		// TODO: assert 404
		fail();
	}
	
	@Test
	@Authenticated
	public void cancelShowsContact()
	{
		Contact contact = contactCreate.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		contactDelete.show(contact)
			.cancel();
		
		boolean actual = this.contact.isVisible();
		
		contactDelete.show(contact)
			.delete();
	
		// TODO: assert correct contact
		assertTrue(actual);
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
		contactDelete.show(1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractPageDriver driver()
	{
		return contactDelete;
	}
}
