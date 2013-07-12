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

import org.openqa.selenium.WebDriver;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Base web UI driver.
 */
public abstract class AbstractDriver
{
	// fields -----------------------------------------------------------------
	
	private final WebDriver webDriver;
	
	private final URL serverUrl;
	
	// constructors -----------------------------------------------------------
	
	public AbstractDriver(WebDriver webDriver, @ServerUrl URL serverUrl)
	{
		this.webDriver = checkNotNull(webDriver, "webDriver");
		this.serverUrl = checkNotNull(serverUrl, "serverUrl");
	}
	
	// public methods ---------------------------------------------------------
	
	public WebDriver driver()
	{
		return webDriver;
	}
	
	public URL getServerUrl()
	{
		return serverUrl;
	}
	
	// protected methods ------------------------------------------------------
	
	protected String url(String spec)
	{
		try
		{
			return new URL(serverUrl, spec).toString();
		}
		catch (MalformedURLException exception)
		{
			throw new IllegalArgumentException(exception);
		}
	}
}
