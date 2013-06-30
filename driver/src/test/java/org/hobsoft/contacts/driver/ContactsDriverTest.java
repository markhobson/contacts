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
package org.hobsoft.contacts.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.mockito.Mockito.mock;

/**
 * Tests {@code ContactsDriver}.
 */
public class ContactsDriverTest
{
	// tests ------------------------------------------------------------------

	@Test(expected = NullPointerException.class)
	public void constructWithNullDriver() throws MalformedURLException
	{
		new ContactsDriver(null, new URL("http://localhost:8080/"));
	}
	
	@Test(expected = NullPointerException.class)
	public void constructWithNullServerUrl()
	{
		new ContactsDriver(mock(WebDriver.class), null);
	}
}
