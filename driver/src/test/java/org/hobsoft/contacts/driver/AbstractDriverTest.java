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

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;

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
		
		AbstractDriver driver = new FakeDriver(config, "x");
		
		assertEquals("configuration", config, driver.getConfiguration());
		assertEquals("selfPathPattern", "x", driver.getSelfPathPattern());
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullDriverConfigurationThrowsException()
	{
		new FakeDriver(null, "x");
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullSelfPathPatternThrowsException() throws MalformedURLException
	{
		new FakeDriver(createConfig(), null);
	}
	
	@Ignore("TODO: fix when Microbrowser mockable")
	@Test
	public void isVisibleWhenVisibleReturnsTrue() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), "x");
		
		assertTrue(driver.isVisible());
	}
	
	@Ignore("TODO: fix when Microbrowser mockable")
	@Test
	public void isVisibleWhenNotVisibleReturnsFalse() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), "x");
		
		assertFalse(driver.isVisible());
	}
	
	@Ignore("TODO: fix when Microbrowser mockable")
	@Test
	public void isVisibleWhenVisibleNullReturnsFalse() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), "x");
		
		assertFalse(driver.isVisible());
	}
	
	@Ignore("TODO: fix when Microbrowser mockable")
	@Test
	public void checkVisibleWhenVisibleReturns() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), "x");
		
		driver.checkVisible();
	}
	
	@Ignore("TODO: fix when Microbrowser mockable")
	@Test
	public void checkVisibleWhenNotVisibleThrowsException() throws MalformedURLException
	{
		AbstractDriver driver = new FakeDriver(createConfig(), "x");
		
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("Expected x");
		
		driver.checkVisible();
	}
	
	@Test
	public void driverReturnsWebDriver() throws MalformedURLException
	{
		WebDriver webDriver = mock(WebDriver.class);
		DriverConfiguration config = new DriverConfiguration(webDriver, createUrl());
		AbstractDriver driver = new FakeDriver(config, "s");
		
		assertEquals(webDriver, driver.driver());
	}
	
	@Test
	public void urlReturnsResolvedUrl() throws MalformedURLException
	{
		DriverConfiguration config = new DriverConfiguration(mock(WebDriver.class), new URL("http://localhost/"));
		AbstractDriver driver = new FakeDriver(config, "s");
		
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
}
