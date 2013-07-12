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

import org.hobsoft.contacts.driver.DriverConfiguration;
import org.hobsoft.contacts.driver.support.selenium.DestroyableWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for acceptance tests.
 */
@Configuration
@ComponentScan(basePackageClasses = DriverConfiguration.class)
public class AcceptanceTestConfig
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_SERVER_PROTOCOL = "http";
	
	private static final String DEFAULT_SERVER_HOST = "localhost";
	
	private static final String SERVER_PORT_PROPERTY = "serverPort";
	
	private static final String DEFAULT_SERVER_PORT = "8080";
	
	private static final String DEFAULT_SERVER_PATH = "/";
	
	// public methods ---------------------------------------------------------
	
	@Bean
	public WebDriver webDriver()
	{
		return new DestroyableWebDriver(new FirefoxDriver());
	}
	
	@Bean
	public DriverConfiguration driverConfiguration() throws MalformedURLException
	{
		return new DriverConfiguration(webDriver(), getServerUrl());
	}
	
	// private methods --------------------------------------------------------
	
	private static URL getServerUrl() throws MalformedURLException
	{
		String protocol = DEFAULT_SERVER_PROTOCOL;
		String host = DEFAULT_SERVER_HOST;
		int port = Integer.valueOf(System.getProperty(SERVER_PORT_PROPERTY, DEFAULT_SERVER_PORT));
		String path = DEFAULT_SERVER_PATH;
		
		return new URL(protocol, host, port, path);
	}
}
