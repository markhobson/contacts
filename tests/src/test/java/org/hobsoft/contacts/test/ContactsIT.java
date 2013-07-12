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
package org.hobsoft.contacts.test;

import org.hobsoft.contacts.driver.ContactsDriver;
import org.hobsoft.contacts.driver.SignInDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Integration test for the contacts page.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AcceptanceTestConfig.class)
public class ContactsIT
{
	// fields -----------------------------------------------------------------
	
	@Autowired
	private SignInDriver signIn;
	
	@Autowired
	private ContactsDriver contacts;
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void contactsDisplaysHeader()
	{
		signIn.signIn("mark", "password");
		contacts.show();
		
		assertTrue(contacts.isVisible());
	}
	
	@Test
	public void contactsDisplaysContacts()
	{
		signIn.signIn("mark", "password");
		contacts.show();
		
		assertTrue(contacts.hasContact("A"));
		assertTrue(contacts.hasContact("B"));
		assertTrue(contacts.hasContact("C"));
	}
}
