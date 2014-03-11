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
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Web UI driver entry point for the application.
 */
@Component
public class ApplicationDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ApplicationDriver(DriverConfiguration config)
	{
		super(config, "/");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public SignInDriver signInForm()
	{
		return new SignInDriver(getConfiguration());
	}

	public ContactsViewDriver contacts()
	{
		home();
		
		return new ContactsViewDriver(getConfiguration());
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractPageDriver methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public SignInDriver signOut()
	{
		home();
		
		return super.signOut();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private void home()
	{
		document().get(url("/"));
	}
}
