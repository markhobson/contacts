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

import java.util.ArrayList;
import java.util.List;

import org.hobsoft.contacts.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hobsoft.contacts.driver.support.selenium.ExpectedConditions2.elementPresent;

/**
 * Web UI driver for the contacts page.
 */
@Component
public class ContactsDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactsDriver(DriverConfiguration config)
	{
		super(config, elementPresent(By.cssSelector("body#contacts")));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public ContactsDriver show()
	{
		driver().get(url("/contacts"));
		
		return this;
	}
	
	public List<Contact> getContacts()
	{
		checkVisible();
		
		List<Contact> contacts = new ArrayList<>();
		
		for (WebElement element : driver().findElements(byItemScope("http://schema.org/Person")))
		{
			contacts.add(parseContact(element));
		}
		
		return contacts;
	}

	public void clickContact(String name)
	{
		checkVisible();
		
		for (WebElement element : driver().findElements(byItemScope("http://schema.org/Person")))
		{
			Contact contact = parseContact(element);
			
			if (name.equals(contact.getName()))
			{
				element.findElement(byItemProp("url")).click();
				return;
			}
		}
		
		throw new IllegalArgumentException("Cannot find contact: " + name);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static Contact parseContact(WebElement element)
	{
		String name = element.findElement(byItemProp("name")).getText();
		
		return new Contact(name);
	}

	private static By byItemScope(String itemType)
	{
		return By.cssSelector(String.format("[itemscope][itemtype='%s']", itemType));
	}

	private static By byItemProp(String itemProp)
	{
		return By.cssSelector(String.format("[itemprop='%s']", itemProp));
	}
}
