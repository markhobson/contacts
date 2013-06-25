package org.hobsoft.contacts.server.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;
import org.hobsoft.contacts.server.dao.ContactDao;
import org.hobsoft.contacts.server.dao.FakeContactDao;

public class ContactsResource
{
	// fields -----------------------------------------------------------------
	
	private final ContactDao contactDao;
	
	// constructors -----------------------------------------------------------
	
	public ContactsResource()
	{
		contactDao = new FakeContactDao();
	}
	
	// public methods ---------------------------------------------------------
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable get()
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("contacts", contactDao.getAll());
		
		return new Viewable("index", model);
	}
}
