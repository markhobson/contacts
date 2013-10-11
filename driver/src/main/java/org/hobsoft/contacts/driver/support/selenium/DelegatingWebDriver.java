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
package org.hobsoft.contacts.driver.support.selenium;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@code WebDriver} implementation that delegates to another.
 */
public abstract class DelegatingWebDriver implements WebDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	private final WebDriver delegate;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	public DelegatingWebDriver(WebDriver delegate)
	{
		this.delegate = checkNotNull(delegate, "delegate");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// WebDriver methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void get(String url)
	{
		delegate.get(url);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCurrentUrl()
	{
		return delegate.getCurrentUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTitle()
	{
		return delegate.getTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WebElement> findElements(By by)
	{
		return delegate.findElements(by);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WebElement findElement(By by)
	{
		return delegate.findElement(by);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPageSource()
	{
		return delegate.getPageSource();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close()
	{
		delegate.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void quit()
	{
		delegate.quit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getWindowHandles()
	{
		return delegate.getWindowHandles();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getWindowHandle()
	{
		return delegate.getWindowHandle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TargetLocator switchTo()
	{
		return delegate.switchTo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Navigation navigate()
	{
		return delegate.navigate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Options manage()
	{
		return delegate.manage();
	}
}
