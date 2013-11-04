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
package org.hobsoft.contacts.driver.contact;

import org.hobsoft.contacts.driver.AbstractPageDriver;
import org.hobsoft.contacts.driver.DriverConfiguration;
import org.hobsoft.contacts.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hobsoft.contacts.driver.support.selenium.ExpectedConditions2.elementPresent;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Web UI driver for the delete contact page.
 */
@Component
public class ContactDeleteDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final ContactsViewDriver contactsDriver;
	
	private final ContactViewDriver contactViewDriver;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactDeleteDriver(DriverConfiguration config, ContactsViewDriver contactsDriver,
		ContactViewDriver contactViewDriver)
	{
		super(config, elementPresent(By.cssSelector("body#contactDelete")));
		
		this.contactsDriver = checkNotNull(contactsDriver, "contactsDriver");
		this.contactViewDriver = checkNotNull(contactViewDriver, "contactViewDriver");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public ContactDeleteDriver show(Contact contact)
	{
		return show(contact.getId());
	}
	
	public ContactDeleteDriver show(long id)
	{
		driver().get(url(String.format("/contact/%d/delete", id)));
		
		return this;
	}

	public Contact getContact()
	{
		checkVisible();
		
		WebElement element = driver().findElement(ByItem.scope("http://schema.org/Person"));
		
		return ContactParser.parse(element);
	}

	public ContactsViewDriver delete()
	{
		checkVisible();
		
		driver().findElement(By.name("submit")).click();
		
		return contactsDriver;
	}

	public ContactViewDriver cancel()
	{
		checkVisible();
		
		driver().findElement(By.id("cancel")).click();
		
		return contactViewDriver;
	}
}
