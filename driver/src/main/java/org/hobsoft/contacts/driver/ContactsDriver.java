package org.hobsoft.contacts.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.google.common.base.Preconditions.checkNotNull;

public class ContactsDriver
{
	// constants --------------------------------------------------------------
	
	private static final String DEFAULT_URL = "http://localhost:8080/";
	
	// fields -----------------------------------------------------------------
	
	private final WebDriver driver;
	
	private final URL serverUrl;
	
	// constructors -----------------------------------------------------------
	
	public ContactsDriver(WebDriver driver)
	{
		this(driver, url(DEFAULT_URL));
	}
	
	public ContactsDriver(WebDriver driver, URL serverUrl)
	{
		this.driver = checkNotNull(driver, "driver");
		this.serverUrl = checkNotNull(serverUrl, "serverUrl");
	}
	
	// public methods ---------------------------------------------------------
	
	public void tearDown()
	{
		driver.quit();
	}
	
	public void contacts()
	{
		driver.get(url(serverUrl, "/contacts").toString());
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
	
	// private methods --------------------------------------------------------

	private static URL url(String spec)
	{
		try
		{
			return new URL(spec);
		}
		catch (MalformedURLException exception)
		{
			throw new IllegalArgumentException(exception);
		}
	}

	private static URL url(URL context, String spec)
	{
		try
		{
			return new URL(context, spec);
		}
		catch (MalformedURLException exception)
		{
			throw new IllegalArgumentException(exception);
		}
	}
}
