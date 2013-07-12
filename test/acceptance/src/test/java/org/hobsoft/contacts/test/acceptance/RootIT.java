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
package org.hobsoft.contacts.test.acceptance;

import org.hobsoft.contacts.driver.ContactsDriver;
import org.hobsoft.contacts.driver.RootDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Acceptance test for the root page.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AcceptanceTestConfig.class)
public class RootIT extends AbstractAuthenticatedIT
{
	// fields -----------------------------------------------------------------
	
	@Autowired
	private RootDriver root;
	
	@Autowired
	private ContactsDriver contacts;
	
	// tests ------------------------------------------------------------------
	
	@Test
	public void rootShowsContacts()
	{
		root.show();
		
		assertTrue(contacts.isVisible());
	}
}