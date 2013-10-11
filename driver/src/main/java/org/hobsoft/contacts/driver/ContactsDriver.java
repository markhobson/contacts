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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Web UI driver for the contacts page.
 */
@Component
public class ContactsDriver extends AbstractDriver
{
	// constructors -----------------------------------------------------------
	
	@Autowired
	public ContactsDriver(DriverConfiguration config)
	{
		super(config);
	}
	
	// AbstractDriver methods -------------------------------------------------
	
	@Override
	public boolean isVisible()
	{
		WebElement body = driver().findElement(By.tagName("body"));
		return "contacts".equals(body.getAttribute("id"));
	}
	
	// public methods ---------------------------------------------------------
	
	public void show()
	{
		driver().get(url("/contacts"));
	}
	
	public boolean hasContact(String name)
	{
		checkVisible();
		
		for (WebElement element : driver().findElements(By.tagName("li")))
		{
			if (name.equals(element.getText()))
			{
				return true;
			}
		}
		
		return false;
	}
}
