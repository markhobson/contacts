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

import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Acceptance test for the application entry point.
 */
public class ApplicationIT extends AbstractIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void pageWhenUnauthenticatedShowsSignIn()
	{
		ui().contacts();
		
		assertTrue(ui().signInForm().isVisible());
	}
	
	@Test
	@Authenticated
	public void pageWhenAuthenticatedShowsContactsView()
	{
		ContactsViewDriver actual = ui().contacts();
		
		assertTrue(actual.isVisible());
	}
}
