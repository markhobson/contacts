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

import org.hobsoft.contacts.client.contact.ContactCollector;
import org.hobsoft.contacts.server.ApplicationConfig;
import org.hobsoft.contacts.test.acceptance.config.ApiDriverConfig;
import org.hobsoft.contacts.test.acceptance.config.UiDriverConfig;
import org.hobsoft.contacts.test.acceptance.rule.RuleConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring configuration for acceptance tests.
 */
@Configuration
@Import({ApiDriverConfig.class, UiDriverConfig.class, RuleConfig.class})
public class AcceptanceTestConfig
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	@Value("http://localhost:#{systemProperties['server.port']?:8080}/")
	private URL serverUrl;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Bean
	public ConfigurableApplicationContext server()
	{
		return new SpringApplicationBuilder(ApplicationConfig.class)
			.properties("spring.main.banner-mode=off")
			.run();
	}
	
	@Bean
	public URL serverUrl()
	{
		return serverUrl;
	}
	
	@Bean(destroyMethod = "quit")
	public WebDriver webDriver()
	{
		return new FirefoxDriver();
	}
	
	@Bean
	public ContactCollector contactCollector()
	{
		return new ContactCollector();
	}
}
