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
package org.hobsoft.contacts.client.contact;

import org.hobsoft.contacts.client.AbstractPageDriver;
import org.hobsoft.contacts.client.DriverConfiguration;
import org.hobsoft.microbrowser.MicrodataDocument;
import org.hobsoft.microbrowser.MicrodataItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hobsoft.contacts.client.MicrodataSchema.PERSON;

/**
 * Driver for the delete contact page.
 */
@Component
public class ContactDeleteDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ContactDeleteDriver(DriverConfiguration config, MicrodataDocument document)
	{
		super(config, document, "/contact/\\d+/delete");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public Contact get()
	{
		checkVisible();
		
		MicrodataItem item = document().getItem(PERSON);
		
		return ContactParser.parse(item);
	}

	public ContactsViewDriver delete()
	{
		checkVisible();
		
		MicrodataDocument document = document().getForm("contactDelete").submit();
		
		return new ContactsViewDriver(getConfiguration(), document);
	}

	public ContactViewDriver cancel()
	{
		checkVisible();

		MicrodataDocument document = document().getLink("cancel").follow();
		
		return new ContactViewDriver(getConfiguration(), document);
	}
}
