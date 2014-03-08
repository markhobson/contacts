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
package org.hobsoft.contacts.test.acceptance.config;

import org.hobsoft.contacts.driver.ApplicationDriver;
import org.hobsoft.contacts.driver.DriverConfiguration;
import org.hobsoft.contacts.driver.auth.SignInDriver;
import org.hobsoft.contacts.driver.auth.SignOutDriver;
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.contacts.driver.event.ContactListener;

/**
 * Spring configuration API for an application driver.
 */
public interface DriverConfig
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	ApplicationDriver applicationDriver(DriverConfiguration config, SignInDriver signIn, SignOutDriver signOut,
		ContactsViewDriver contactsView);
	
	SignInDriver signInDriver(DriverConfiguration config);
	
	SignOutDriver signOutDriver(DriverConfiguration config);
	
	ContactsViewDriver contactsViewDriver(DriverConfiguration config, ContactListener contactListener);
}
