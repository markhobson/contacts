package org.hobsoft.contacts.server.resource;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;
import org.hobsoft.contacts.server.dao.ContactDao;

import static com.google.common.base.Preconditions.checkNotNull;

@Path("/contacts")
public class ContactsResource
{
	// fields -----------------------------------------------------------------
	
	private final ContactDao contactDao;
	
	// constructors -----------------------------------------------------------
	
	@Inject
	public ContactsResource(ContactDao contactDao)
	{
		this.contactDao = checkNotNull(contactDao, "contactDao");
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
