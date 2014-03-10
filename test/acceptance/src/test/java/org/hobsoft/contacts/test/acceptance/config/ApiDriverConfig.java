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
import org.hobsoft.contacts.driver.event.ContactListener;
import org.hobsoft.contacts.driver.support.microbrowser.StatefulMicrobrowser;
import org.hobsoft.microbrowser.jsoup.JsoupMicrobrowser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for the API application driver.
 */
@Configuration
public class ApiDriverConfig
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Bean(name = "apiApplicationDriver")
	@API
	public ApplicationDriver applicationDriver(@API DriverConfiguration config, ContactListener contactListener)
	{
		return new ApplicationDriver(config, contactListener);
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
