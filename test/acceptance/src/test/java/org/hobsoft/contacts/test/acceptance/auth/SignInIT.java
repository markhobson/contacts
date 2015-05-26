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

import org.hobsoft.contacts.client.auth.Credentials;
import org.hobsoft.contacts.client.auth.SignInDriver;
import org.hobsoft.contacts.client.contact.ContactsViewDriver;
import org.hobsoft.contacts.test.acceptance.AbstractIT;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hobsoft.contacts.test.acceptance.auth.AcceptanceTestCredentials.USER;
import static org.junit.Assert.assertThat;

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
		SignInDriver signInForm = ui().signInForm();
		
		assertThat(signInForm.isVisible(), is(true));
	}
	
	@Test
	public void pageWhenUnauthenticatedDoesNotShowSignOut()
	{
		SignInDriver signInForm = ui().signInForm();
		
		assertThat(signInForm.isSignOutVisible(), is(false));
	}
	
	@Test
	public void signInWithKnownCredentialsShowsContactsView()
	{
		ContactsViewDriver contactsView = ui().signInForm()
			.signIn(USER);
		
		assertThat(contactsView.isVisible(), is(true));
	}
	
	@Test
	public void signInWithUnknownCredentialsShowsError()
	{
		SignInDriver signInForm = ui().signInForm()
			.signInWithError(new Credentials("mark", ""));
		
		assertThat(signInForm.getErrorMessage(), is("Incorrect username or password."));
	}
}
