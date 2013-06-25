package org.hobsoft.contacts.test;

import org.hobsoft.contacts.driver.ContactsDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class ContactsTest
{
	private ContactsDriver driver;
	
	@Before
	public void setUp()
	{
		driver = new ContactsDriver(new FirefoxDriver());
	}
	
	@After
	public void tearDown()
	{
		driver.tearDown();
	}
	
	@Test
	public void contacts()
	{
		driver.contacts();
		
		assertEquals("Contacts", driver.webDriver().findElement(By.tagName("h1")).getText());
	}
}
