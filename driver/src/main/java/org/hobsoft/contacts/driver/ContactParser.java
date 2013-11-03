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

import org.hobsoft.contacts.model.Contact;
import org.openqa.selenium.WebElement;

import static org.hobsoft.contacts.driver.support.selenium.WebDriverUtils.quietFindElementBy;

/**
 * Microdata parser for contacts.
 */
final class ContactParser
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	private ContactParser()
	{
		throw new AssertionError();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public static Contact parse(WebElement element)
	{
		String name = getItemValue(element.findElement(ByItem.prop("name")));
		Contact contact = new Contact(name);
		
		String url = getItemValue(quietFindElementBy(element, ByItem.prop("url")));
		if (url != null)
		{
			contact.setId(parseId(url));
		}
		
		return contact;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------
	
	private static String getItemValue(WebElement element)
	{
		if (element == null)
		{
			return null;
		}
		
		String tagName = element.getTagName();
		String value;
		
		if ("link".equals(tagName))
		{
			value = element.getAttribute("href");
		}
		else
		{
			value = element.getText();
		}
		
		return value;
	}

	private static Long parseId(String url)
	{
		int lastSlash = url.lastIndexOf('/');
		String idString = url.substring(lastSlash + 1);
		
		return Long.valueOf(idString);
	}
}
