package org.hobsoft.contacts.driver;

import org.junit.Test;

public class ContactsDriverTest
{
	// tests ------------------------------------------------------------------

	@Test(expected = NullPointerException.class)
	public void constructWithNullDriver()
	{
		new ContactsDriver(null);
	}
}
