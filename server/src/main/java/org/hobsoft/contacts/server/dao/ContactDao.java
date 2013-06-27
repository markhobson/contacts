package org.hobsoft.contacts.server.dao;

import java.util.List;

import org.hobsoft.contacts.server.model.Contact;

/**
 * DAO for contacts.
 */
public interface ContactDao
{
	// public methods ---------------------------------------------------------
	
	List<Contact> getAll();
}
