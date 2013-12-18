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
import java.util.regex.Pattern;

import org.hobsoft.contacts.driver.support.microbrowser.StatefulMicrobrowser;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Base web UI driver.
 */
public abstract class AbstractDriver implements Driver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	private final DriverConfiguration config;
	
	private final String selfPathPattern;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	public AbstractDriver(DriverConfiguration config, String selfPathPattern)
	{
		this.config = checkNotNull(config, "config");
		this.selfPathPattern = checkNotNull(selfPathPattern, "selfPathPattern");
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
		if (!browser().hasDocument())
		{
			return false;
		}
		
		String self = browser().getDocument().getLink("self").getHref();
		String selfPath = quietNewUrl(self).getPath();
		
		return Pattern.matches(selfPathPattern, selfPath);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public final DriverConfiguration getConfiguration()
	{
		return config;
	}
	
	public final String getSelfPathPattern()
	{
		return selfPathPattern;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// protected methods
	// ----------------------------------------------------------------------------------------------------------------
	
	protected final void checkVisible()
	{
		checkState(isVisible(), "Expected self: " + selfPathPattern);
	}
	
	protected final StatefulMicrobrowser browser()
	{
		return config.getBrowser();
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
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static URL quietNewUrl(String spec)
	{
		try
		{
			return new URL(spec);
		}
		catch (MalformedURLException exception)
		{
			throw new IllegalArgumentException("Invalid URL: " + spec);
		}
	}
}
