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
import org.hobsoft.contacts.driver.event.ContactListener;
import org.hobsoft.contacts.driver.support.microbrowser.StatefulMicrobrowser;
import org.hobsoft.microbrowser.selenium.SeleniumMicrobrowser;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for the UI application driver.
 */
@Configuration
public class UiApplicationDriverConfig
{
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
	public DriverConfiguration uiDriverConfiguration(@UI StatefulMicrobrowser microbrowser, URL serverUrl)
	{
		return new DriverConfiguration(microbrowser, serverUrl);
	}
	
	@Bean
	@UI
	public StatefulMicrobrowser microbrowser(WebDriver webDriver)
	{
		return new StatefulMicrobrowser(new SeleniumMicrobrowser(webDriver));
	}
}
