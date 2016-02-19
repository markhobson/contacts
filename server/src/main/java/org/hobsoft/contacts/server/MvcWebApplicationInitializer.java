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
package org.hobsoft.contacts.server;

import javax.servlet.Filter;

import org.hobsoft.contacts.server.controller.ControllerConfig;
import org.hobsoft.contacts.server.repository.RepositoryConfig;
import org.hobsoft.contacts.server.security.SecurityConfig;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring MVC {@code WebApplicationInitializer} for the application.
 */
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractAnnotationConfigDispatcherServletInitializer methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class<?>[] {
			RepositoryConfig.class,
			SecurityConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class<?>[] {ControllerConfig.class};
	}

	// ----------------------------------------------------------------------------------------------------------------
	// AbstractDispatcherServletInitializer methods
	// ----------------------------------------------------------------------------------------------------------------

	@Override
	protected String[] getServletMappings()
	{
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters()
	{
		return new Filter[] {new HiddenHttpMethodFilter()};
	}
}