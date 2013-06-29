package org.hobsoft.contacts.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.hobsoft.contacts.driver.ContactsDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactsIT
{
	private static final String DEFAULT_SERVER_PROTOCOL = "http";
	
	private static final String DEFAULT_SERVER_HOST = "localhost";
	
	private static final String SERVER_PORT_PROPERTY = "serverPort";
	
	private static final String DEFAULT_SERVER_PORT = "8080";
	
	private static final String DEFAULT_SERVER_PATH = "/";
	
	private ContactsDriver driver;
	
	@Before
	public void setUp() throws MalformedURLException
	{
		driver = new ContactsDriver(new FirefoxDriver(), getServerUrl());
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
	
	private static URL getServerUrl() throws MalformedURLException
	{
		String protocol = DEFAULT_SERVER_PROTOCOL;
		String host = DEFAULT_SERVER_HOST;
		int port = Integer.valueOf(System.getProperty(SERVER_PORT_PROPERTY, DEFAULT_SERVER_PORT));
		String path = DEFAULT_SERVER_PATH;
		
		return new URL(protocol, host, port, path);
	}
}
