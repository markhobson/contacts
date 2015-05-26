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
package org.hobsoft.contacts.client.auth;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code Credentials}.
 */
public class CredentialsTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void constructorSetsProperties()
	{
		Credentials actual = new Credentials("x", "y");
		
		assertThat("username", actual.getUsername(), is("x"));
		assertThat("password", actual.getPassword(), is("y"));
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullUsernameThrowsException()
	{
		new Credentials(null, "x");
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullPasswordThrowsException()
	{
		new Credentials("x", null);
	}
}
