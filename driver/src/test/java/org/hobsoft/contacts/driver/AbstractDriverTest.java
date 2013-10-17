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
package org.hobsoft.contacts.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static org.hobsoft.contacts.driver.support.selenium.ExpectedConditions2.never;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Tests {@code AbstractDriver}.
 */
public class AbstractDriverTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private ExpectedException thrown = ExpectedException.none();
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void constructorSetsProperties() throws MalformedURLException
	{
		DriverConfiguration config = createConfig();
		ExpectedCondition<Boolean> visibleCondition = never();
		
		AbstractDriver driver = new FakeDriver(config, visibleCondition);
		
		assertEquals("configuration", config, driver.getConfiguration());
		assertEquals("visibleCondition", visibleCondition, driver.getVisibleCondition());
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullDriverConfigurationThrowsException()
	{
		new FakeDriver(null, never());
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullVisibleConditionThrowsException() throws MalformedURLException
	{
		new FakeDriver(createConfig(), null);
	}
	
	@Test
	public void isVisibleWhenVisibleReturnsTrue() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), constant(true));
		
		assertTrue(driver.isVisible());
	}
	
	@Test
	public void isVisibleWhenNotVisibleReturnsFalse() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), constant(false));
		
		assertFalse(driver.isVisible());
	}
	
	@Test
	public void isVisibleWhenVisibleNullReturnsFalse() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), constant(null));
		
		assertFalse(driver.isVisible());
	}
	
	@Test
	public void checkVisibleWhenVisibleReturns() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), constant(true));
		
		driver.checkVisible();
	}
	
	@Test
	public void checkVisibleWhenNotVisibleThrowsException() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), constant(false, "x"));
		
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("Expected x");
		
		driver.checkVisible();
	}
	
	@Test
	public void driverReturnsWebDriver() throws MalformedURLException
	{
		WebDriver webDriver = mock(WebDriver.class);
		DriverConfiguration config = new DriverConfiguration(webDriver, createUrl());
		AbstractDriver driver = new FakeDriver(config, never());
		
		assertEquals(webDriver, driver.driver());
	}
	
	@Test
	public void urlReturnsResolvedUrl() throws MalformedURLException
	{
		DriverConfiguration config = new DriverConfiguration(mock(WebDriver.class), new URL("http://localhost/"));
		AbstractDriver driver = new FakeDriver(config, never());
		
		assertEquals("http://localhost/x", driver.url("x"));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Rule
	public ExpectedException getThrown()
	{
		return thrown;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static DriverConfiguration createConfig() throws MalformedURLException
	{
		return new DriverConfiguration(mock(WebDriver.class), createUrl());
	}

	private static URL createUrl() throws MalformedURLException
	{
		return new URL("http://localhost/");
	}
	
	private static ExpectedCondition<Boolean> constant(final Boolean value)
	{
		return constant(value, String.valueOf(value));
	}
	
	private static ExpectedCondition<Boolean> constant(final Boolean value, final String message)
	{
		return new ExpectedCondition<Boolean>()
		{
			@Override
			public Boolean apply(WebDriver driver)
			{
				return value;
			}
			
			@Override
			public String toString()
			{
				return message;
			}
		};
	}
}
