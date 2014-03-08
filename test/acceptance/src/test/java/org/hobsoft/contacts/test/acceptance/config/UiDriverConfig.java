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
package org.hobsoft.contacts.test.acceptance.config;

import java.net.URL;

import org.hobsoft.contacts.driver.ApplicationDriver;
import org.hobsoft.contacts.driver.DriverConfiguration;
import org.hobsoft.contacts.driver.auth.SignInDriver;
import org.hobsoft.contacts.driver.auth.SignOutDriver;
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
public class UiDriverConfig implements DriverConfig
{
	// ----------------------------------------------------------------------------------------------------------------
	// DriverConfig methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Override
	@Bean
	@UI
	public ApplicationDriver applicationDriver(@UI DriverConfiguration config, ContactListener contactListener,
		@UI SignInDriver signIn, @UI SignOutDriver signOut)
	{
		return new ApplicationDriver(config, contactListener, signIn, signOut);
	}
	
	@Override
	@Bean
	@UI
	public SignInDriver signInDriver(@UI DriverConfiguration config)
	{
		return new SignInDriver(config);
	}
	
	@Override
	@Bean
	@UI
	public SignOutDriver signOutDriver(@UI DriverConfiguration config)
	{
		return new SignOutDriver(config);
	}
	
	@Bean
	@UI
	public DriverConfiguration driverConfiguration(@UI StatefulMicrobrowser microbrowser, URL serverUrl)
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
