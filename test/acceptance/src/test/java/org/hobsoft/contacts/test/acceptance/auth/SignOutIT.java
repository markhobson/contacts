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
package org.hobsoft.contacts.test.acceptance.auth;

import org.hobsoft.contacts.driver.ApplicationDriver;
import org.hobsoft.contacts.test.acceptance.AbstractIT;
import org.hobsoft.contacts.test.acceptance.UI;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Acceptance test for signing out page.
 */
public class SignOutIT extends AbstractIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	@UI
	private ApplicationDriver ui;
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	@Authenticated
	public void signOutShowsSignIn()
	{
		ui.signOut()
			.signOut();
		
		assertTrue(ui.signIn().isVisible());
	}
	
	@Test
	@Authenticated
	public void signOutShowsSuccessMessage()
	{
		ui.signOut()
			.signOut();
		
		assertEquals("You have been signed out.", ui.signIn().getSuccessMessage());
	}
}
