package org.hobsoft.contacts.driver;

import org.openqa.selenium.WebDriver;

public class ContactsDriver
{
	// fields -----------------------------------------------------------------
	
	private final WebDriver driver;
	
	// constructors -----------------------------------------------------------
	
	public ContactsDriver(WebDriver driver)
	{
		this.driver = driver;
	}
	
	// public methods ---------------------------------------------------------
	
	public void tearDown()
	{
		driver.quit();
	}
	
	public WebDriver webDriver()
	{
		return driver;
	}

	public void contacts()
	{
		driver.get("http://localhost:8080/contacts");
	}
}
