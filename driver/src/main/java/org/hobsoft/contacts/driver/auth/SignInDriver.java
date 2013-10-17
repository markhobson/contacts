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

import org.hobsoft.contacts.driver.AbstractDriver;
import org.hobsoft.contacts.driver.DriverConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Web UI driver for the sign-in page.
 */
@Component
public class SignInDriver extends AbstractDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public SignInDriver(DriverConfiguration config)
	{
		super(config);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// Driver methods
	// ----------------------------------------------------------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible()
	{
		WebElement body = driver().findElement(By.tagName("body"));
		return "login".equals(body.getAttribute("id"));
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public SignInDriver show()
	{
		driver().get(url("/login"));
		
		return this;
	}
	
	public void signIn(Credentials credentials)
	{
		checkVisible();
		
		driver().findElement(By.name("username")).sendKeys(credentials.getUsername());
		driver().findElement(By.name("password")).sendKeys(credentials.getPassword());
		driver().findElement(By.name("submit")).click();
	}
}
