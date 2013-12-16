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

import org.hobsoft.contacts.model.Contact;
import org.hobsoft.microbrowser.MicrodataItem;
import org.hobsoft.microbrowser.MicrodataProperty;

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

	public static Contact parse(MicrodataItem item)
	{
		String name = item.getProperty("name").getValue();
		Contact contact = new Contact(name);
		
		MicrodataProperty urlProperty = quietGetProperty(item, "url");
		if (urlProperty != null)
		{
			contact.setId(parseId(urlProperty.getValue()));
		}
		
		return contact;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------
	
	private static MicrodataProperty quietGetProperty(MicrodataItem item, String name)
	{
		try
		{
			return item.getProperty(name);
		}
		catch (IllegalArgumentException exception)
		{
			return null;
		}
	}
	
	private static Long parseId(String url)
	{
		int lastSlash = url.lastIndexOf('/');
		String idString = url.substring(lastSlash + 1);
		
		return Long.valueOf(idString);
	}
}
