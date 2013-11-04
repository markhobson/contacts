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

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests {@code WebDriverUtils}.
 */
public class WebDriverUtilsTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void quietFindElementByWithKnownElementReturnsElement()
	{
		SearchContext context = mock(SearchContext.class);
		By by = mock(By.class);
		WebElement element = mock(WebElement.class);
		when(context.findElement(by)).thenReturn(element);
		
		assertEquals(element, WebDriverUtils.quietFindElementBy(context, by));
	}
	
	@Test
	public void quietFindElementByWithDriverAndUnknownElementReturnsNull()
	{
		SearchContext context = mock(SearchContext.class);
		By by = mock(By.class);
		when(context.findElement(by)).thenThrow(new NoSuchElementException(""));
		
		assertNull(WebDriverUtils.quietFindElementBy(context, by));
	}
}
