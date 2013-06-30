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

import org.hobsoft.contacts.driver.ServerUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

/**
 * Guice module for integration tests.
 */
public class ContactsITModule extends AbstractModule
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_SERVER_PROTOCOL = "http";
	
	private static final String DEFAULT_SERVER_HOST = "localhost";
	
	private static final String SERVER_PORT_PROPERTY = "serverPort";
	
	private static final String DEFAULT_SERVER_PORT = "8080";
	
	private static final String DEFAULT_SERVER_PATH = "/";
	
	// AbstractModule methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure()
	{
		bind(WebDriver.class).to(FirefoxDriver.class).in(Scopes.SINGLETON);
	}
	
	// private methods --------------------------------------------------------

	@Provides
	@ServerUrl
	private URL serverUrl() throws MalformedURLException
	{
		String protocol = DEFAULT_SERVER_PROTOCOL;
		String host = DEFAULT_SERVER_HOST;
		int port = Integer.valueOf(System.getProperty(SERVER_PORT_PROPERTY, DEFAULT_SERVER_PORT));
		String path = DEFAULT_SERVER_PATH;
		
		return new URL(protocol, host, port, path);
	}
}
