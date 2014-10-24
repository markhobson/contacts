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
package org.hobsoft.contacts.server.support.spring.mustache;

import org.springframework.beans.factory.InitializingBean;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

/**
 * Spring bean to configure Mustache for {@code MustacheView}.
 * 
 * @see MustacheView
 */
public class MustacheConfigurer implements MustacheConfig, InitializingBean
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private MustacheFactory mustacheFactory;

	// ----------------------------------------------------------------------------------------------------------------
	// InitializingBean methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterPropertiesSet() throws Exception
	{
		if (mustacheFactory == null)
		{
			setMustacheFactory(new DefaultMustacheFactory());
		}
	}

	// ----------------------------------------------------------------------------------------------------------------
	// MustacheConfig methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MustacheFactory getMustacheFactory()
	{
		return mustacheFactory;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public void setMustacheFactory(MustacheFactory mustacheFactory)
	{
		this.mustacheFactory = mustacheFactory;
	}
}
