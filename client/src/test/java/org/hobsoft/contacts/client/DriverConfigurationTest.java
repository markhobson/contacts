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
package org.hobsoft.contacts.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.hobsoft.contacts.client.contact.ContactListener;
import org.hobsoft.microbrowser.Microbrowser;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Tests {@code DriverConfiguration}.
 */
public class DriverConfigurationTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test(expected = NullPointerException.class)
	public void constructorWithNullBrowserThrowsException() throws MalformedURLException
	{
		new DriverConfiguration(null, newUrl(), mock(ContactListener.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullServerUrlThrowsException()
	{
		new DriverConfiguration(mock(Microbrowser.class), null, mock(ContactListener.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullContactListenerThrowsException() throws MalformedURLException
	{
		new DriverConfiguration(mock(Microbrowser.class), newUrl(), null);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static URL newUrl() throws MalformedURLException
	{
		return new URL("http://localhost/");
	}
}
