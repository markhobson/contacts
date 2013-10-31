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
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

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
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Ignore("Reinstate when contact page embeds id")
	@Test
	@Authenticated
	public void pageShowsForm()
	{
		Contact contact = contactCreate.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		contactDelete.show(contact.getId());
		
		assertThat(contactDelete.getContact(), samePropertyValuesAs(new Contact("x")));
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
