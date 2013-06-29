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

import org.glassfish.jersey.server.mvc.MvcProperties;

/**
 * Jersey MVC template processor properties for Mustache.java.
 */
public final class MustacheProperties
{
	// constants --------------------------------------------------------------
	
	public static final String TEMPLATES_BASE_PATH = MvcProperties.TEMPLATE_BASE_PATH + ".mustache";
	
	// constructors -----------------------------------------------------------
	
	private MustacheProperties()
	{
		throw new AssertionError();
	}
}
