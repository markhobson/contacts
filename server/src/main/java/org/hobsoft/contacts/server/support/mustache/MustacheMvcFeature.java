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
package org.hobsoft.contacts.server.support.mustache;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.jersey.server.mvc.MvcFeature;

/**
 * Jersey MVC feature for Mustache.java.
 */
@ConstrainedTo(RuntimeType.SERVER)
public class MustacheMvcFeature implements Feature
{
	// Feature methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean configure(FeatureContext context)
	{
		if (!context.getConfiguration().isRegistered(MvcFeature.class))
		{
			context.register(MvcFeature.class);
		}
		
		context.register(MustacheViewProcessor.class);
		
		return true;
	}
}
