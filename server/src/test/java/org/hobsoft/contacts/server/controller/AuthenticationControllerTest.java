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
package org.hobsoft.contacts.server.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@code AuthenticationController}.
 */
public class AuthenticationControllerTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private AuthenticationController controller;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Before
	public void setUp()
	{
		controller = new AuthenticationController();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------
	
	@Test
	public void loginFormWithLogoutAddsAttribute()
	{
		ModelAndView actual = controller.loginForm("", null);
		
		assertEquals("logout", actual.getModel().get("logout"));
	}
	
	@Test
	public void loginFormWithErrorAddsAttribute()
	{
		ModelAndView actual = controller.loginForm(null, "");
		
		assertEquals("error", actual.getModel().get("error"));
	}
	
	@Test
	public void loginFormReturnsView()
	{
		ModelAndView actual = controller.loginForm(null, null);
		
		assertEquals("auth/login", actual.getViewName());
	}
}