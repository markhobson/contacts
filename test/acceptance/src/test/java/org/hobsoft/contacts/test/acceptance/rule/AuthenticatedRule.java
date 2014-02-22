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

import org.hobsoft.contacts.driver.ApplicationDriver;
import org.hobsoft.contacts.driver.auth.Credentials;
import org.hobsoft.contacts.test.acceptance.config.UI;
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
	
	private final ApplicationDriver ui;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	@Autowired
	public AuthenticatedRule(@UI ApplicationDriver ui)
	{
		this.ui = checkNotNull(ui, "ui");
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
				ui.signIn()
					.show()
					.signIn(credentials);
				
				try
				{
					base.evaluate();
				}
				finally
				{
					ui.signOut()
						.signOut();
				}
			}
		};
	}
}
