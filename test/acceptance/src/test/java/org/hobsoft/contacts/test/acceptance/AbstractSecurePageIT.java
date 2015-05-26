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

import org.hobsoft.contacts.client.AbstractPageDriver;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Base acceptance test for pages that require authentication.
 */
public abstract class AbstractSecurePageIT extends AbstractIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Ignore("TODO: how to navigate to secured page with HATEOAS?")
	@Test
	public final void pageWhenUnauthenticatedShowsSignIn()
	{
		show();
		
		assertThat(ui().signInForm().isVisible(), is(true));
	}
	
	@Test
	public final void pageWhenAuthenticatedIsVisible()
	{
		AbstractPageDriver page = show();
		
		assertThat(page.isVisible(), is(true));
	}
	
	@Test
	public final void pageShowsSignOut()
	{
		AbstractPageDriver page = show();
		
		assertThat(page.isSignOutVisible(), is(true));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// protected methods
	// ----------------------------------------------------------------------------------------------------------------

	protected abstract AbstractPageDriver show();
}
