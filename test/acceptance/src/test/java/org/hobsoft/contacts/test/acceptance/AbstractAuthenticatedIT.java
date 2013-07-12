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

import org.hobsoft.contacts.driver.SignInDriver;
import org.hobsoft.contacts.driver.SignOutDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base acceptance test for a page that requires authentication.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AcceptanceTestConfig.class)
public abstract class AbstractAuthenticatedIT
{
	// fields -----------------------------------------------------------------
	
	@Autowired
	private SignInDriver signIn;
	
	@Autowired
	private SignOutDriver signOut;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public final void setUpAuthentication()
	{
		signIn.show();
		signIn.signIn("mark", "password");
	}
	
	@After
	public final void tearDownAuthentication()
	{
		signOut.signOut();
	}
}
