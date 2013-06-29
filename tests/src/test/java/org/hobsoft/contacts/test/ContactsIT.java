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
package org.hobsoft.contacts.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.hobsoft.contacts.driver.ContactsDriver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Integration test for the contacts page.
 */
public class ContactsIT
{
	private static final String DEFAULT_SERVER_PROTOCOL = "http";
	
	private static final String DEFAULT_SERVER_HOST = "localhost";
	
	private static final String SERVER_PORT_PROPERTY = "serverPort";
	
	private static final String DEFAULT_SERVER_PORT = "8080";
	
	private static final String DEFAULT_SERVER_PATH = "/";
	
	private WebDriverProvider webDriverProvider = new WebDriverProvider()
	{
		@Override
		protected WebDriver createWebDriver()
		{
			return new FirefoxDriver();
		}
	};
	
	private ContactsDriver driver;
	
	@Rule
	public WebDriverProvider webDriverProvider()
	{
		return webDriverProvider;
	}
	
	@Before
	public void setUp() throws MalformedURLException
	{
		driver = new ContactsDriver(webDriverProvider().get(), getServerUrl());
	}
	
	@Test
	public void contactsDisplaysHeader()
	{
		driver.contacts();
		
		assertEquals("Contacts", driver.getHeader());
	}
	
	@Test
	public void contactsDisplaysContacts()
	{
		driver.contacts();
		
		assertTrue(driver.hasContact("A"));
		assertTrue(driver.hasContact("B"));
		assertTrue(driver.hasContact("C"));
	}
	
	private static URL getServerUrl() throws MalformedURLException
	{
		String protocol = DEFAULT_SERVER_PROTOCOL;
		String host = DEFAULT_SERVER_HOST;
		int port = Integer.valueOf(System.getProperty(SERVER_PORT_PROPERTY, DEFAULT_SERVER_PORT));
		String path = DEFAULT_SERVER_PATH;
		
		return new URL(protocol, host, port, path);
	}
}
