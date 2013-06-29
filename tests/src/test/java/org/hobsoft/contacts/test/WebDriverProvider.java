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

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;

import static com.google.common.base.Preconditions.checkState;

/**
 * Rule for creating and destroying a shared {@code WebDriver} instance.
 */
public abstract class WebDriverProvider extends ExternalResource
{
	// fields -----------------------------------------------------------------
	
	private WebDriver driver;
	
	// ExternalResource methods -----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void before()
	{
		driver = createWebDriver();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void after()
	{
		driver.quit();
		
		driver = null;
	}
	
	// public methods ---------------------------------------------------------
	
	public WebDriver get()
	{
		checkState(driver != null, "WebDriver instance not available");
		
		return driver;
	}
	
	// protected methods ------------------------------------------------------
	
	protected abstract WebDriver createWebDriver();
}
