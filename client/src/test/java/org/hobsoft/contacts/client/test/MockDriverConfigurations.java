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
package org.hobsoft.contacts.client.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.hobsoft.contacts.client.DriverConfiguration;
import org.hobsoft.contacts.client.contact.ContactListener;
import org.hobsoft.microbrowser.Microbrowser;

import static org.mockito.Mockito.mock;

/**
 * Factory for {@code DriverConfiguration} mocks.
 */
public final class MockDriverConfigurations
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	private MockDriverConfigurations()
	{
		throw new AssertionError();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public static DriverConfiguration newConfig() throws MalformedURLException
	{
		return new DriverConfiguration(mock(Microbrowser.class), newUrl(), mock(ContactListener.class));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static URL newUrl() throws MalformedURLException
	{
		return new URL("http://localhost/");
	}
}
