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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code Contact}.
 */
public class ContactTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void constructorSetsProperties()
	{
		Contact contact = new Contact();
		
		assertThat("id", contact.getId(), is(nullValue()));
		assertThat("name", contact.getName(), isEmptyString());
	}

	@Test
	public void constructorWithNameSetsProperties()
	{
		Contact contact = new Contact("x");
		
		assertThat("id", contact.getId(), is(nullValue()));
		assertThat("name", contact.getName(), is("x"));
	}

	@Test
	public void setIdSetsProperty()
	{
		Contact contact = new Contact();
		
		contact.setId(1L);
		
		assertThat(contact.getId(), is(1L));
	}
	
	@Test
	public void setIdWithNullSetsProperty()
	{
		Contact contact = new Contact();
		
		contact.setId(null);
		
		assertThat(contact.getId(), is(nullValue()));
	}
	
	@Test
	public void setNameSetsProperty()
	{
		Contact contact = new Contact();
		
		contact.setName("x");
		
		assertThat(contact.getName(), is("x"));
	}
	
	@Test(expected = NullPointerException.class)
	public void setNameWithNullThrowsException()
	{
		Contact contact = new Contact();
		
		contact.setName(null);
	}
}
