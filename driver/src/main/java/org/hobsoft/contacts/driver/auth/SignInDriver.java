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
package org.hobsoft.contacts.driver.auth;

import org.hobsoft.contacts.driver.AbstractPageDriver;
import org.hobsoft.contacts.driver.DriverConfiguration;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hobsoft.contacts.driver.support.selenium.ExpectedConditions2.elementPresent;

/**
 * Web UI driver for the sign-in page.
 */
@Component
public class SignInDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public SignInDriver(DriverConfiguration config)
	{
		super(config, elementPresent(By.cssSelector("body#login")));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public SignInDriver show()
	{
		browser().get(url("/login"));
		
		return this;
	}

	public String getSuccessMessage()
	{
		checkVisible();
		
		return driver().findElement(By.id("success")).getText();
	}

	public String getErrorMessage()
	{
		checkVisible();
		
		return driver().findElement(By.id("error")).getText();
	}
	
	public void signIn(Credentials credentials)
	{
		checkVisible();

		document().getForm("login")
			.setParameter("username", credentials.getUsername())
			.setParameter("password", credentials.getPassword())
			.submit();
	}
}
