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
import org.openqa.selenium.WebDriver;
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
	public void quietFindElementByWithDriverAndKnownElementReturnsElement()
	{
		WebDriver driver = mock(WebDriver.class);
		By by = mock(By.class);
		WebElement element = mock(WebElement.class);
		when(driver.findElement(by)).thenReturn(element);
		
		assertEquals(element, WebDriverUtils.quietFindElementBy(driver, by));
	}
	
	@Test
	public void quietFindElementByWithDriverAndUnknownElementReturnsNull()
	{
		WebDriver driver = mock(WebDriver.class);
		By by = mock(By.class);
		when(driver.findElement(by)).thenThrow(new NoSuchElementException(""));
		
		assertNull(WebDriverUtils.quietFindElementBy(driver, by));
	}
	
	@Test
	public void quietFindElementByWithElementAndKnownElementReturnsElement()
	{
		WebElement element = mock(WebElement.class);
		By by = mock(By.class);
		WebElement child = mock(WebElement.class);
		when(element.findElement(by)).thenReturn(child);
		
		assertEquals(child, WebDriverUtils.quietFindElementBy(element, by));
	}
	
	@Test
	public void quietFindElementByWithElementAndUnknownElementReturnsNull()
	{
		WebElement element = mock(WebElement.class);
		By by = mock(By.class);
		when(element.findElement(by)).thenThrow(new NoSuchElementException(""));
		
		assertNull(WebDriverUtils.quietFindElementBy(element, by));
	}
}
