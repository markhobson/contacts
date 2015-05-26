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
package org.hobsoft.contacts.server.controller;

import org.hobsoft.contacts.server.model.Contact;
import org.hobsoft.contacts.server.support.spring.hateoas.AbstractResourceAssembler;
import org.hobsoft.contacts.server.support.spring.hateoas.Relation;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Spring Hateoas resource assembler for contacts.
 */
@Component
public class ContactResourceAssembler extends AbstractResourceAssembler<Contact, Resource<Contact>>
{
	// ----------------------------------------------------------------------------------------------------------------
	// ResourceAssembler methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	public Resource<Contact> toResource(Contact contact)
	{
		Resource<Contact> resource = new Resource<>(contact);
		
		resource.add(linkTo(methodOn(ContactsController.class).get(contact.getId()))
			.withSelfRel());
		
		resource.add(linkTo(methodOn(ContactsController.class).getAll())
			.withRel(Relation.COLLECTION.rel()));
		
		resource.add(linkTo(methodOn(ContactsController.class).deleteForm(contact.getId()))
			.withRel(Relation.DELETE.rel()));
		
		return resource;
	}
}
