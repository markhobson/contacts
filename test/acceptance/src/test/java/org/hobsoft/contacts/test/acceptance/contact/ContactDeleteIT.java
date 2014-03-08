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
import org.hobsoft.contacts.driver.contact.ContactViewDriver;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.contacts.test.acceptance.AbstractSecurePageIT;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.hobsoft.microbrowser.MicrobrowserException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
		Contact contact = api().contactsView()
			.create()
			.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		Contact actual = ui().contactDelete()
			.show(contact)
			.getContact();
		
		assertThat(actual.getName(), is("x"));
	}
	
	@Test
	@Authenticated
	public void deleteDeletesContact()
	{
		Contact contact = api().contactsView()
			.create()
			.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		ui().contactDelete()
			.show(contact)
			.delete();
		
		ContactViewDriver contactView = api().contactView();
		thrown.expect(MicrobrowserException.class);
		contactView.show(contact);
	}
	
	@Test
	@Authenticated
	public void cancelShowsContactView()
	{
		Contact contact = api().contactsView()
			.create()
			.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		ui().contactDelete()
			.show(contact)
			.cancel();
		
		// TODO: assert correct contact
		assertTrue(ui().contactView().isVisible());
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
		Contact contact = api().contactsView()
			.create()
			.show()
			.setContact(new Contact("x"))
			.create()
			.getContact();
		
		ui().contactDelete()
			.show(contact);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractPageDriver driver()
	{
		return ui().contactDelete();
	}
}
