package org.hobsoft.contacts.server.model;

import static com.google.common.base.Preconditions.checkNotNull;

public class Contact
{
	// fields -----------------------------------------------------------------
	
	private String name;
	
	// constructors -----------------------------------------------------------
	
	public Contact()
	{
		setName("");
	}
	
	// public methods ---------------------------------------------------------
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = checkNotNull(name, "name");
	}
}
