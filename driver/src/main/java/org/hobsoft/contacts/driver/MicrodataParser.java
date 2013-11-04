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

import org.openqa.selenium.WebElement;

/**
 * Selenium WebDriver to Microdata parser.
 */
final class MicrodataParser
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	private MicrodataParser()
	{
		throw new AssertionError();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public static String getItemValue(WebElement element)
	{
		if (element == null)
		{
			return null;
		}
		
		String tagName = element.getTagName();
		String value;
		
		if ("link".equals(tagName) || "a".equals(tagName))
		{
			value = element.getAttribute("href");
		}
		else
		{
			value = element.getText();
		}
		
		return value;
	}
}
