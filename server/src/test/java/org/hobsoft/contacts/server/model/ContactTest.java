package org.hobsoft.contacts.server.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactTest
{
	// tests ------------------------------------------------------------------
	
	@Test
	public void construct()
	{
		Contact contact = new Contact();
		
		assertEquals("", contact.getName());
	}

	@Test
	public void setName()
	{
		Contact contact = new Contact();
		
		contact.setName("x");
		
		assertEquals("x", contact.getName());
	}
	
	@Test(expected = NullPointerException.class)
	public void setNameWithNull()
	{
		Contact contact = new Contact();
		
		contact.setName(null);
	}
}
