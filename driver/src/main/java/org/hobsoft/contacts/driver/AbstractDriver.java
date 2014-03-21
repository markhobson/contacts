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

import org.hobsoft.microbrowser.MicrodataDocument;

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
	
	private final MicrodataDocument document;
	
	private final String selfPathPattern;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	public AbstractDriver(DriverConfiguration config, MicrodataDocument document, String selfPathPattern)
	{
		this.config = checkNotNull(config, "config");
		this.document = checkNotNull(document, "document");
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
		return Pattern.matches(selfPathPattern, getSelfPath());
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
		checkState(isVisible(), "Self expected: %s but was: %s", selfPathPattern, getSelfPath());
	}
	
	protected final MicrodataDocument document()
	{
		return document;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private String getSelfPath()
	{
		if (document() == null)
		{
			return "";
		}
		
		String self = document().getLink("self").getHref();
		return quietNewUrl(self).getPath();
	}
	
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
