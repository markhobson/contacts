package org.hobsoft.contacts.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.google.common.base.Preconditions.checkNotNull;

public class ContactsDriver
{
	// fields -----------------------------------------------------------------
	
	private final WebDriver driver;
	
	// constructors -----------------------------------------------------------
	
	public ContactsDriver(WebDriver driver)
	{
		this.driver = checkNotNull(driver, "driver");
	}
	
	// public methods ---------------------------------------------------------
	
	public void tearDown()
	{
		driver.quit();
	}
	
	public void contacts()
	{
		driver.get("http://localhost:8080/contacts");
	}
	
	public String getHeader()
	{
		return driver.findElement(By.tagName("h1")).getText();
	}
	
	public boolean hasContact(String name)
	{
		for (WebElement element : driver.findElements(By.tagName("li")))
		{
			if (name.equals(element.getText()))
			{
				return true;
			}
		}
		
		return false;
	}
}
