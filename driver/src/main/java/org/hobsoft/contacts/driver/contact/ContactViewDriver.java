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
import org.hobsoft.contacts.driver.support.microbrowser.SeleniumMicrodataDocument;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.microbrowser.MicrodataDocument;
import org.hobsoft.microbrowser.MicrodataItem;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hobsoft.contacts.driver.support.selenium.ExpectedConditions2.elementPresent;

/**
 * Web UI driver for the view contact page.
 */
@Component
public class ContactViewDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactViewDriver(DriverConfiguration config)
	{
		super(config, elementPresent(By.cssSelector("body#contactView")));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public ContactViewDriver show(Contact contact)
	{
		return show(contact.getId());
	}
	
	public ContactViewDriver show(long id)
	{
		driver().get(url("/contact/" + id));
		
		return this;
	}

	public Contact getContact()
	{
		checkVisible();
		
		MicrodataDocument document = new SeleniumMicrodataDocument(driver());
		MicrodataItem item = document.getItem("http://schema.org/Person");
		
		return ContactParser.parse(item);
	}
}
