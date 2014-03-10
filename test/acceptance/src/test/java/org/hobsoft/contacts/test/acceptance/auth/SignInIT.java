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

import org.hobsoft.contacts.driver.auth.Credentials;
import org.hobsoft.contacts.test.acceptance.AbstractIT;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Acceptance test for the sign in page.
 */
public class SignInIT extends AbstractIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void pageWhenUnauthenticatedShowsSignIn()
	{
		ui().signInForm()
			.show();
		
		assertTrue(ui().signInForm().isVisible());
	}
	
	@Test
	@Authenticated
	public void pageWhenAuthenticatedIsVisible()
	{
		ui().signInForm()
			.show();
		
		assertTrue(ui().signInForm().isVisible());
	}
	
	@Test
	public void pageWhenUnauthenticatedDoesNotShowSignOut()
	{
		ui().signInForm()
			.show();
		
		assertFalse(ui().signInForm().isSignOutVisible());
	}
	
	@Test
	@Authenticated
	public void pageWhenAuthenticatedShowsSignOut()
	{
		ui().signInForm()
			.show();
		
		assertTrue(ui().signInForm().isSignOutVisible());
	}
	
	@Test
	public void signInWithKnownCredentialsShowsContactsView()
	{
		ui().signInForm()
			.show()
			.signIn(new Credentials("mark", "password"));
		
		assertTrue(ui().contacts().isVisible());
	}
	
	@Test
	public void signInWithUnknownCredentialsShowsError()
	{
		ui().signInForm()
			.show()
			.signIn(new Credentials("mark", ""));
		
		assertEquals("Incorrect username or password.", ui().signInForm().getErrorMessage());
	}
}
