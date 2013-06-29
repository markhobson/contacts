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
package org.hobsoft.contacts.server.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code Contact}.
 */
public class ContactTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void construct()
	{
		Contact contact = new Contact();
		
		assertEquals("", contact.getName());
	}

	@Test
	public void setName()
	{
		Contact contact = new Contact();
		
		contact.setName("x");
		
		assertEquals("x", contact.getName());
	}
	
	@Test(expected = NullPointerException.class)
	public void setNameWithNull()
	{
		Contact contact = new Contact();
		
		contact.setName(null);
	}
}
