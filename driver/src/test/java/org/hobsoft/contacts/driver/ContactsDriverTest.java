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

import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.microbrowser.MicrodataDocument;
import org.junit.Test;

import static org.hobsoft.contacts.driver.test.MockDriverConfigurations.newConfig;
import static org.mockito.Mockito.mock;

/**
 * Tests {@code ContactsDriver}.
 */
public class ContactsDriverTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test(expected = NullPointerException.class)
	public void constructorWithNullConfigurationThrowsException()
	{
		new ContactsViewDriver(null, mock(MicrodataDocument.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullDocumentThrowsException() throws MalformedURLException
	{
		new ContactsViewDriver(newConfig(), null);
	}
}
