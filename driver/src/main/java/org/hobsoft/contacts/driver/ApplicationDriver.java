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
package org.hobsoft.contacts.driver;

import org.hobsoft.contacts.driver.auth.SignInDriver;
import org.hobsoft.contacts.driver.auth.SignOutDriver;
import org.hobsoft.contacts.driver.contact.ContactDeleteDriver;
import org.hobsoft.contacts.driver.contact.ContactViewDriver;
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Web UI driver entry point for the application.
 */
@Component
public class ApplicationDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final RootDriver root;
	
	private final SignInDriver signIn;

	private final SignOutDriver signOut;
	
	private final ContactsViewDriver contactsView;

	private final ContactViewDriver contactView;
	
	private final ContactDeleteDriver contactDelete;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ApplicationDriver(RootDriver root, SignInDriver signIn, SignOutDriver signOut,
		ContactsViewDriver contactsView, ContactViewDriver contactView, ContactDeleteDriver contactDelete)
	{
		this.root = root;
		this.signIn = signIn;
		this.signOut = signOut;
		this.contactsView = contactsView;
		this.contactView = contactView;
		this.contactDelete = contactDelete;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public void show()
	{
		root.show();
	}

	public SignInDriver signIn()
	{
		return signIn;
	}

	public SignOutDriver signOut()
	{
		return signOut;
	}

	public ContactsViewDriver contactsView()
	{
		return contactsView;
	}

	public ContactViewDriver contactView()
	{
		return contactView;
	}

	public ContactDeleteDriver contactDelete()
	{
		return contactDelete;
	}
}
