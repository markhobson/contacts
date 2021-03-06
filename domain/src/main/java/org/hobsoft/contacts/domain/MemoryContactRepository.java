/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hobsoft.contacts.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * In-memory contact repository implementation.
 */
public class MemoryContactRepository implements ContactRepository
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final Map<Long, Contact> contactsById;
	
	private long nextId;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public MemoryContactRepository()
	{
		contactsById = new LinkedHashMap<>();
		nextId = 1;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// ContactRepository methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Override
	public void create(Contact contact)
	{
		Long id = contact.getId();
		
		if (id == null)
		{
			id = nextId++;
			contact.setId(id);
		}
		
		contactsById.put(id, contact);
	}

	@Override
	public List<Contact> getAll()
	{
		return ImmutableList.copyOf(contactsById.values());
	}
	
	@Override
	public Contact get(long id)
	{
		Contact contact = contactsById.get(id);
		
		if (contact == null)
		{
			throw new IllegalArgumentException("Contact not found: " + id);
		}
		
		return contact;
	}
	
	@Override
	public void delete(Contact contact)
	{
		Long id = contact.getId();
		checkArgument(id != null, "Cannot delete transient entity: " + contact);
		
		contactsById.remove(id);
	}
}
