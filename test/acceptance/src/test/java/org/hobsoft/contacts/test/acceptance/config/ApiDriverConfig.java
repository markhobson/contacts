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
import org.hobsoft.microbrowser.jsoup.JsoupMicrobrowser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for the API application driver.
 */
@Configuration
public class ApiDriverConfig implements DriverConfig
{
	// ----------------------------------------------------------------------------------------------------------------
	// DriverConfig methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Override
	@Bean(name = "apiApplicationDriver")
	@API
	public ApplicationDriver applicationDriver(@API DriverConfiguration config, ContactListener contactListener,
		@API SignInDriver signIn, @API SignOutDriver signOut)
	{
		return new ApplicationDriver(config, contactListener, signIn, signOut);
	}
	
	@Override
	@Bean(name = "apiSignInDriver")
	@API
	public SignInDriver signInDriver(@API DriverConfiguration config)
	{
		return new SignInDriver(config);
	}
	
	@Override
	@Bean(name = "apiSignOutDriver")
	@API
	public SignOutDriver signOutDriver(@API DriverConfiguration config)
	{
		return new SignOutDriver(config);
	}
	
	@Bean(name = "apiDriverConfiguration")
	@API
	public DriverConfiguration driverConfiguration(@API StatefulMicrobrowser microbrowser, URL serverUrl)
	{
		return new DriverConfiguration(microbrowser, serverUrl);
	}
	
	@Bean(name = "apiMicrobrowser")
	@API
	public StatefulMicrobrowser microbrowser()
	{
		return new StatefulMicrobrowser(new JsoupMicrobrowser());
	}
}
