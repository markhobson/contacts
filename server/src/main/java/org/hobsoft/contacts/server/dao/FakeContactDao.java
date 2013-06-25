package org.hobsoft.contacts.server.dao;

import java.util.Arrays;
import java.util.List;

import org.hobsoft.contacts.server.model.Contact;

public class FakeContactDao implements ContactDao
{
	// ContactDao methods -----------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public List<Contact> getAll()
	{
		return Arrays.asList(
			createContact("A"),
			createContact("B"),
			createContact("C")
		);
	}
	
	// private methods --------------------------------------------------------

	private Contact createContact(String name)
	{
		Contact contact = new Contact();
		contact.setName(name);
		return contact;
	}
}
