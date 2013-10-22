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

import org.hobsoft.contacts.driver.AbstractPageDriver;
import org.hobsoft.contacts.driver.auth.SignInDriver;
import org.hobsoft.contacts.test.acceptance.rule.Authenticated;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * Base acceptance test for pages that require authentication.
 */
public abstract class AbstractSecurePageIT extends AbstractIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private SignInDriver signIn;

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public final void pageWhenUnauthenticatedShowsSignIn()
	{
		show();
		
		assertTrue(signIn.isVisible());
	}
	
	@Test
	@Authenticated
	public final void pageWhenAuthenticatedIsVisible()
	{
		show();
		
		assertTrue(driver().isVisible());
	}
	
	@Test
	@Authenticated
	public final void pageShowsSignOut()
	{
		show();
		
		assertTrue(driver().isSignOutVisible());
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// protected methods
	// ----------------------------------------------------------------------------------------------------------------

	protected abstract void show();
	
	protected abstract AbstractPageDriver driver();
}
