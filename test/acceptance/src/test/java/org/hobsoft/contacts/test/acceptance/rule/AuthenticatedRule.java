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
package org.hobsoft.contacts.test.acceptance.rule;

import org.hobsoft.contacts.driver.auth.Credentials;
import org.hobsoft.contacts.driver.auth.SignInDriver;
import org.hobsoft.contacts.driver.auth.SignOutDriver;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * JUnit rule to sign-in and sign-out around tests annotated with {@code @Authenticated}.
 */
@Component
public class AuthenticatedRule implements TestRule
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	private final SignInDriver signIn;
	
	private final SignOutDriver signOut;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	@Autowired
	public AuthenticatedRule(SignInDriver signIn, SignOutDriver signOut)
	{
		this.signIn = checkNotNull(signIn, "signIn");
		this.signOut = checkNotNull(signOut, "signOut");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// TestRule methods
	// ----------------------------------------------------------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Statement apply(final Statement base, Description description)
	{
		Authenticated authenticated = description.getAnnotation(Authenticated.class);
		
		if (authenticated == null)
		{
			return base;
		}
		
		final Credentials credentials = new Credentials(authenticated.username(), authenticated.password());
		
		return new Statement()
		{
			@Override
			// CHECKSTYLE:OFF
			public void evaluate() throws Throwable
			// CHECKSTYLE:ON
			{
				signIn.show();
				signIn.signIn(credentials);
				
				try
				{
					base.evaluate();
				}
				finally
				{
					signOut.signOut();
				}
			}
		};
	}
}
