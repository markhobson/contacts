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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Factory for {@code ExpectedCondition}s that are not provided by {@code ExpectedConditions}.
 * 
 * @see org.openqa.selenium.support.ui.ExpectedConditions
 */
public final class ExpectedConditions2
{
	// ----------------------------------------------------------------------------------------------------------------
	// constants
	// ----------------------------------------------------------------------------------------------------------------

	private static final ExpectedCondition<Boolean> NEVER = new ExpectedCondition<Boolean>()
	{
		@Override
		public Boolean apply(WebDriver driver)
		{
			return false;
		}
		
		@Override
		public String toString()
		{
			return "never";
		}
	};

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	private ExpectedConditions2()
	{
		throw new AssertionError();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public static ExpectedCondition<Boolean> never()
	{
		return NEVER;
	}

	public static ExpectedCondition<Boolean> elementPresent(final By by)
	{
		checkNotNull(by, "by");
		
		return new ExpectedCondition<Boolean>()
		{
			@Override
			public Boolean apply(WebDriver driver)
			{
				return !driver.findElements(by).isEmpty();
			}
			
			@Override
			public String toString()
			{
				return by.toString();
			}
		};
	}
}
