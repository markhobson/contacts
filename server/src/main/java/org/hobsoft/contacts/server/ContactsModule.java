package org.hobsoft.contacts.server;

import org.hobsoft.contacts.server.dao.ContactDao;
import org.hobsoft.contacts.server.dao.FakeContactDao;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;

public class ContactsModule extends ServletModule
{
	// ServletModule methods --------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configureServlets()
	{
		bind(ContactDao.class).to(FakeContactDao.class).in(Scopes.SINGLETON);
	}
}
