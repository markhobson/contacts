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

import org.hobsoft.contacts.driver.contact.ContactDeleteDriver;
import org.hobsoft.contacts.driver.contact.ContactViewDriver;
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
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
	@Authenticated
	public void pageShowsName()
	{
		api().contacts()
			.create(new Contact("x"));
		
		Contact actual = ui().contacts()
			.contact("x")
			.deleteForm()
			.get();
		
		assertThat(actual.getName(), is("x"));
	}
	
	@Test
	@Authenticated
	public void deleteDeletesContact()
	{
		api().contacts()
			.create(new Contact("x"));
		
		ui().contacts()
			.contact("x")
			.deleteForm()
			.delete();
		
		ContactsViewDriver contactsView = api().contacts();
		thrown.expect(IllegalArgumentException.class);
		contactsView.contact("x");
	}
	
	@Test
	@Authenticated
	public void cancelShowsContactView()
	{
		Contact contact = api().contacts()
			.create(new Contact("x"))
			.get();
		
		ContactViewDriver actual = ui().contacts()
			.contact("x")
			.deleteForm()
			.cancel();
		
		assertThat(actual.get(), samePropertyValuesAs(contact));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractSecurePageIT methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ContactDeleteDriver show()
	{
		api().contacts()
			.create(new Contact("x"));
		
		return ui().contacts()
			.contact("x")
			.deleteForm();
	}
}
