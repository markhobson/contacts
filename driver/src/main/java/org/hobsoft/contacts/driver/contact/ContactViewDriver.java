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
import org.hobsoft.contacts.driver.event.ContactListener;
import org.hobsoft.contacts.model.Contact;
import org.hobsoft.microbrowser.MicrodataItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Web UI driver for the view contact page.
 */
@Component
public class ContactViewDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final ContactListener contactListener;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactViewDriver(DriverConfiguration config, ContactListener contactListener)
	{
		super(config, "/contact/\\d+");
		
		this.contactListener = checkNotNull(contactListener, "contactListener");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public Contact get()
	{
		checkVisible();
		
		MicrodataItem item = document().getItem("http://schema.org/Person");
		
		return ContactParser.parse(item);
	}
	
	public ContactsViewDriver delete()
	{
		return deleteForm().delete();
	}

	public ContactDeleteDriver deleteForm()
	{
		checkVisible();
		
		document().getLink("delete").follow();
		
		return new ContactDeleteDriver(getConfiguration(), contactListener);
	}
}
