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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * High-level web UI driver for the Contacts application.
 */
@Component
public class ContactsDriver
{
	// fields -----------------------------------------------------------------
	
	private final WebDriver driver;
	
	private final URL serverUrl;
	
	// constructors -----------------------------------------------------------
	
	@Autowired
	public ContactsDriver(WebDriver driver, @ServerUrl URL serverUrl)
	{
		this.driver = checkNotNull(driver, "driver");
		this.serverUrl = checkNotNull(serverUrl, "serverUrl");
	}
	
	// public methods ---------------------------------------------------------
	
	public void contacts()
	{
		driver.get(url(serverUrl, "/contacts").toString());
	}
	
	public String getHeader()
	{
		return driver.findElement(By.tagName("h1")).getText();
	}
	
	public boolean hasContact(String name)
	{
		for (WebElement element : driver.findElements(By.tagName("li")))
		{
			if (name.equals(element.getText()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	// private methods --------------------------------------------------------

	private static URL url(URL context, String spec)
	{
		try
		{
			return new URL(context, spec);
		}
		catch (MalformedURLException exception)
		{
			throw new IllegalArgumentException(exception);
		}
	}
}
