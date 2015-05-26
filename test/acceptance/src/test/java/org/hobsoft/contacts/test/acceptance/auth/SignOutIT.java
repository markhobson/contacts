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

import org.hobsoft.contacts.client.auth.SignInDriver;
import org.hobsoft.contacts.test.acceptance.AbstractIT;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hobsoft.contacts.test.acceptance.auth.AcceptanceTestCredentials.USER;
import static org.junit.Assert.assertThat;

/**
 * Acceptance test for signing out page.
 */
public class SignOutIT extends AbstractIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void signOutShowsSignIn()
	{
		SignInDriver signInForm = ui().signIn(USER)
			.signOut();
		
		assertThat(signInForm.isVisible(), is(true));
	}
	
	@Test
	public void signOutShowsSuccessMessage()
	{
		SignInDriver signInForm = ui().signIn(USER)
			.signOut();
		
		assertThat(signInForm.getSuccessMessage(), is("You have been signed out."));
	}
}
