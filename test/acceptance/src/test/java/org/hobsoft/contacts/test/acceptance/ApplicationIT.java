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

import org.hobsoft.contacts.client.contact.ContactsViewDriver;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hobsoft.contacts.test.acceptance.auth.AcceptanceTestCredentials.USER;
import static org.junit.Assert.assertThat;

/**
 * Acceptance test for the application entry point.
 */
public class ApplicationIT extends AbstractIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void pageWhenAuthenticatedShowsContactsView()
	{
		ContactsViewDriver contactsView = ui().signIn(USER);
		
		assertThat(contactsView.isVisible(), is(true));
	}
}
