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

import org.hobsoft.contacts.driver.contact.ContactCreateDriver;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Test;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Acceptance test for the create contact page.
 */
public class ContactCreateIT extends AbstractSecurePageIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	@Authenticated
	public void pageShowsForm()
	{
		Contact actual = ui().contactsView()
			.create()
			.show()
			.getContact();
		
		assertThat(actual, samePropertyValuesAs(new Contact("")));
	}
	
	@Test
	@Authenticated
	public void submitCreatesContact()
	{
		Contact actual = ui().contactsView()
			.create()
			.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		Contact expected = new Contact("x");
		expected.setId(actual.getId());
		assertThat(actual, samePropertyValuesAs(expected));
	}
	
	@Test
	@Authenticated
	public void cancelShowsContactsView()
	{
		ui().contactsView()
			.create()
			.show()
			.cancel();
		
		assertTrue(ui().contactsView().isVisible());
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
		driver().show();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ContactCreateDriver driver()
	{
		return ui().contactsView()
			.create();
	}
}
