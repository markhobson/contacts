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

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Base web UI driver.
 */
public abstract class AbstractDriver implements Driver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constants
	// ----------------------------------------------------------------------------------------------------------------

	private static final long VISIBLE_TIMEOUT = 1;

	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	private final DriverConfiguration config;
	
	private final ExpectedCondition<Boolean> visibleCondition;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	public AbstractDriver(DriverConfiguration config, ExpectedCondition<Boolean> visibleCondition)
	{
		this.config = checkNotNull(config, "config");
		this.visibleCondition = checkNotNull(visibleCondition, "visibleCondition");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// Driver methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isVisible()
	{
		try
		{
			new WebDriverWait(driver(), VISIBLE_TIMEOUT).until(visibleCondition);
			return true;
		}
		catch (TimeoutException exception)
		{
			return false;
		}
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public final DriverConfiguration getConfiguration()
	{
		return config;
	}
	
	public final ExpectedCondition<Boolean> getVisibleCondition()
	{
		return visibleCondition;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// protected methods
	// ----------------------------------------------------------------------------------------------------------------
	
	protected final void checkVisible()
	{
		checkState(isVisible(), "Expected " + visibleCondition);
	}
	
	protected final WebDriver driver()
	{
		return config.getWebDriver();
	}
	
	protected final String url(String spec)
	{
		try
		{
			return new URL(config.getServerUrl(), spec).toString();
		}
		catch (MalformedURLException exception)
		{
			throw new IllegalArgumentException(exception);
		}
	}
}
