package org.hobsoft.contacts.test;

import org.hobsoft.contacts.driver.ContactsDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void contactsDisplaysHeader()
	{
		driver.contacts();
		
		assertEquals("Contacts", driver.getHeader());
	}
	
	@Test
	public void contactsDisplaysContacts()
	{
		driver.contacts();
		
		assertTrue(driver.hasContact("A"));
		assertTrue(driver.hasContact("B"));
		assertTrue(driver.hasContact("C"));
	}
}
