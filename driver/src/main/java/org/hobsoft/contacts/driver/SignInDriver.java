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
package org.hobsoft.contacts.driver;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Web UI driver for the sign-in page.
 */
@Component
public class SignInDriver extends AbstractDriver
{
	// constructors -----------------------------------------------------------
	
	@Autowired
	public SignInDriver(WebDriver driver, @ServerUrl URL serverUrl)
	{
		super(driver, serverUrl);
	}
	
	// public methods ---------------------------------------------------------
	
	public void show()
	{
		getDriver().get(url("/login"));
	}
	
	public void signIn(String username, String password)
	{
		show();
		
		getDriver().findElement(By.name("username")).sendKeys(username);
		getDriver().findElement(By.name("password")).sendKeys(password);
		getDriver().findElement(By.name("submit")).click();
	}
}
