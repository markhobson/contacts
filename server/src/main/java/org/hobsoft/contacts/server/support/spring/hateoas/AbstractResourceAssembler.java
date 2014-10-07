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
package org.hobsoft.contacts.server.support.spring.hateoas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;

/**
 * Base resource assembler that provides support for collections.
 * 
 * @param <T>
 *            the entity type
 * @param <D>
 *            the resource type
 */
public abstract class AbstractResourceAssembler<T, D extends ResourceSupport> implements ResourceAssembler<T, D>
{
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public List<D> toResources(Iterable<? extends T> entities)
	{
		List<D> resources = new ArrayList<>();
		
		for (T entity : entities)
		{
			resources.add(toResource(entity));
		}
		
		return resources;
	}
}
