package org.hobsoft.contacts.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class ContactsTest
{
	private WebDriver driver;
	
	@Before
	public void setUp()
	{
		driver = new FirefoxDriver();
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test
	public void contacts()
	{
		driver.get("http://localhost:8080/contacts");
		
		assertEquals("Contacts", driver.findElement(By.tagName("h1")).getText());
	}
}
