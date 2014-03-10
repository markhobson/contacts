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
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.contacts.driver.event.ContactListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Web UI driver entry point for the application.
 */
@Component
public class ApplicationDriver extends AbstractDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final ContactListener contactListener;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ApplicationDriver(DriverConfiguration config, ContactListener contactListener)
	{
		super(config, "/");
		
		this.contactListener = checkNotNull(contactListener, "contactListener");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public SignInDriver signIn()
	{
		return new SignInDriver(getConfiguration());
	}

	public SignOutDriver signOut()
	{
		return new SignOutDriver(getConfiguration());
	}

	public ContactsViewDriver contacts()
	{
		document().get(url("/"));

		return new ContactsViewDriver(getConfiguration(), contactListener);
	}
}
