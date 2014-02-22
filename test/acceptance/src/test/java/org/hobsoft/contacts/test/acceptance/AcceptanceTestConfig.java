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

import java.net.MalformedURLException;
import java.net.URL;

import org.hobsoft.contacts.driver.ApplicationDriver;
import org.hobsoft.contacts.driver.DriverConfiguration;
import org.hobsoft.contacts.driver.RootDriver;
import org.hobsoft.contacts.driver.auth.SignInDriver;
import org.hobsoft.contacts.driver.auth.SignOutDriver;
import org.hobsoft.contacts.driver.contact.ContactCreateDriver;
import org.hobsoft.contacts.driver.contact.ContactDeleteDriver;
import org.hobsoft.contacts.driver.contact.ContactViewDriver;
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.contacts.driver.event.ContactCollector;
import org.hobsoft.contacts.driver.event.ContactListener;
import org.hobsoft.contacts.driver.support.microbrowser.StatefulMicrobrowser;
import org.hobsoft.contacts.test.acceptance.rule.AuthenticatedRule;
import org.hobsoft.microbrowser.selenium.SeleniumMicrobrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for acceptance tests.
 */
@Configuration
@ComponentScan(basePackageClasses = AuthenticatedRule.class)
public class AcceptanceTestConfig
{
	// ----------------------------------------------------------------------------------------------------------------
	// constants
	// ----------------------------------------------------------------------------------------------------------------
	
	private static final String DEFAULT_SERVER_PROTOCOL = "http";
	
	private static final String DEFAULT_SERVER_HOST = "localhost";
	
	private static final String SERVER_PORT_PROPERTY = "serverPort";
	
	private static final String DEFAULT_SERVER_PORT = "8080";
	
	private static final String DEFAULT_SERVER_PATH = "/";
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Bean
	@UI
	public ApplicationDriver uiDriver(@UI RootDriver root, @UI SignInDriver signIn, @UI SignOutDriver signOut,
		@UI ContactsViewDriver contactsView, @UI ContactViewDriver contactView, @UI ContactCreateDriver contactCreate,
		@UI ContactDeleteDriver contactDelete)
	{
		return new ApplicationDriver(root, signIn, signOut, contactsView, contactView, contactCreate, contactDelete);
	}
	
	@Bean
	@UI
	public RootDriver uiRootDriver(@UI DriverConfiguration config)
	{
		return new RootDriver(config);
	}
	
	@Bean
	@UI
	public SignInDriver uiSignInDriver(@UI DriverConfiguration config)
	{
		return new SignInDriver(config);
	}
	
	@Bean
	@UI
	public SignOutDriver uiSignOutDriver(@UI DriverConfiguration config)
	{
		return new SignOutDriver(config);
	}
	
	@Bean
	@UI
	public ContactsViewDriver uiContactsViewDriver(@UI DriverConfiguration config)
	{
		return new ContactsViewDriver(config);
	}
	
	@Bean
	@UI
	public ContactViewDriver uiContactViewDriver(@UI DriverConfiguration config)
	{
		return new ContactViewDriver(config);
	}
	
	@Bean
	@UI
	public ContactCreateDriver uiContactCreateDriver(@UI DriverConfiguration config, ContactListener contactListener,
		@UI ContactViewDriver contactView, @UI ContactsViewDriver contactsView)
	{
		return new ContactCreateDriver(config, contactListener, contactView, contactsView);
	}
	
	@Bean
	@UI
	public ContactDeleteDriver uiContactDeleteDriver(@UI DriverConfiguration config,
		@UI ContactsViewDriver contactsView, @UI ContactViewDriver contactView)
	{
		return new ContactDeleteDriver(config, contactsView, contactView);
	}
	
	@Bean
	@UI
	public DriverConfiguration uiDriverConfiguration(@UI StatefulMicrobrowser microbrowser) throws MalformedURLException
	{
		return new DriverConfiguration(microbrowser, getServerUrl());
	}
	
	@Bean
	public ContactCollector contactCollector()
	{
		return new ContactCollector();
	}
	
	@Bean
	@UI
	public StatefulMicrobrowser microbrowser(WebDriver webDriver)
	{
		return new StatefulMicrobrowser(new SeleniumMicrobrowser(webDriver));
	}
	
	@Bean(destroyMethod = "quit")
	public WebDriver webDriver()
	{
		return new FirefoxDriver();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------
	
	private static URL getServerUrl() throws MalformedURLException
	{
		String protocol = DEFAULT_SERVER_PROTOCOL;
		String host = DEFAULT_SERVER_HOST;
		int port = Integer.valueOf(System.getProperty(SERVER_PORT_PROPERTY, DEFAULT_SERVER_PORT));
		String path = DEFAULT_SERVER_PATH;
		
		return new URL(protocol, host, port, path);
	}
}
