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

import org.hobsoft.contacts.driver.ContactsDriver;
import org.hobsoft.contacts.driver.auth.Credentials;
import org.hobsoft.contacts.driver.auth.SignInDriver;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Acceptance test for the sign in page.
 */
public class SignInIT extends AbstractIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private SignInDriver signIn;
	
	@Autowired
	private ContactsDriver contacts;
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void pageWhenUnauthenticatedShowsSignIn()
	{
		signIn.show();
		
		assertTrue(signIn.isVisible());
	}
	
	@Test
	@Authenticated
	public void pageWhenAuthenticatedIsVisible()
	{
		signIn.show();
		
		assertTrue(signIn.isVisible());
	}
	
	@Test
	public void signInWithKnownCredentialsShowsContacts()
	{
		signIn.show()
			.signIn(new Credentials("mark", "password"));
		
		assertTrue(contacts.isVisible());
	}
	
	@Test
	public void signInWithUnknownCredentialsShowsError()
	{
		signIn.show()
			.signIn(new Credentials("mark", ""));
		
		assertEquals("Incorrect username or password.", signIn.getErrorMessage());
	}
}
