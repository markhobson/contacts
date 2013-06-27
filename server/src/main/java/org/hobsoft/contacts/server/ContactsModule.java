package org.hobsoft.contacts.server;

import org.hobsoft.contacts.server.dao.ContactDao;
import org.hobsoft.contacts.server.dao.FakeContactDao;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * Main Guice module.
 */
public class ContactsModule extends AbstractModule
{
	// AbstractModule methods -------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure()
	{
		bind(ContactDao.class).to(FakeContactDao.class).in(Scopes.SINGLETON);
	}
}
