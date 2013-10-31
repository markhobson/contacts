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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hobsoft.contacts.driver.support.selenium.ExpectedConditions2.elementPresent;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Web UI driver for the create contact page.
 */
@Component
public class ContactCreateDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final ContactDriver contactDriver;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactCreateDriver(DriverConfiguration config, ContactDriver contactDriver)
	{
		super(config, elementPresent(By.cssSelector("body#contactCreate")));
		
		this.contactDriver = checkNotNull(contactDriver, "contactDriver");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public ContactCreateDriver show()
	{
		driver().get(url("/contacts/create"));
		
		return this;
	}

	public Contact getContact()
	{
		checkVisible();
		
		WebElement element = driver().findElement(ByItem.scope("http://schema.org/Person"));
		
		return ContactParser.parse(element);
	}

	public ContactCreateDriver setContact(Contact contact)
	{
		checkVisible();
		
		WebElement element = driver().findElement(ByItem.scope("http://schema.org/Person"));

		element.findElement(ByItem.prop("name")).sendKeys(contact.getName());
		
		return this;
	}

	public ContactDriver create()
	{
		checkVisible();
		
		driver().findElement(By.name("submit")).click();
		
		return contactDriver;
	}
}
