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

import java.util.Collections;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static java.util.Collections.singletonList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests {@code ExpectedConditions2}.
 */
public class ExpectedConditions2Test
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void neverReturnsExpectedCondition()
	{
		assertNotNull(ExpectedConditions2.never());
	}

	@Test
	public void neverApplyReturnsFalse()
	{
		ExpectedCondition<Boolean> actual = ExpectedConditions2.never();
		
		assertEquals(Boolean.FALSE, actual.apply(mock(WebDriver.class)));
	}
	
	@Test
	public void neverToStringReturnsMessage()
	{
		ExpectedCondition<Boolean> actual = ExpectedConditions2.never();
		
		assertEquals("never", actual.toString());
	}
	
	@Test
	public void elementPresentReturnsExpectedCondition()
	{
		assertNotNull(ExpectedConditions2.elementPresent(mock(By.class)));
	}

	@Test(expected = NullPointerException.class)
	public void elementPresentWithNullByThrowsException()
	{
		ExpectedConditions2.elementPresent(null);
	}
	
	@Test
	public void elementPresentApplyWhenPresentReturnsTrue()
	{
		WebDriver driver = mock(WebDriver.class);
		By by = mock(By.class);
		when(driver.findElements(by)).thenReturn(singletonList(mock(WebElement.class)));
		
		ExpectedCondition<Boolean> actual = ExpectedConditions2.elementPresent(by);
		
		assertEquals(Boolean.TRUE, actual.apply(driver));
	}

	@Test
	public void elementPresentApplyWhenNotPresentReturnsFalse()
	{
		WebDriver driver = mock(WebDriver.class);
		By by = mock(By.class);
		when(driver.findElements(by)).thenReturn(Collections.<WebElement>emptyList());
		
		ExpectedCondition<Boolean> actual = ExpectedConditions2.elementPresent(by);
		
		assertEquals(Boolean.FALSE, actual.apply(driver));
	}

	@Test
	public void elementPresenToStringReturnsMessage()
	{
		By by = mock(By.class);
		
		ExpectedCondition<Boolean> actual = ExpectedConditions2.elementPresent(by);
		
		assertEquals(by.toString(), actual.toString());
	}
}
