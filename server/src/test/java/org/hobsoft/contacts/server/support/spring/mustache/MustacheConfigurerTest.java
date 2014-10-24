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

import org.junit.Before;
import org.junit.Test;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Tests {@code MustacheConfigurer}.
 */
public class MustacheConfigurerTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private MustacheConfigurer configurer;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		configurer = new MustacheConfigurer();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void afterPropertiesSetWhenMustacheFactoryNotSetSetsProperty() throws Exception
	{
		configurer.afterPropertiesSet();
		
		assertThat(configurer.getMustacheFactory(), is(instanceOf(DefaultMustacheFactory.class)));
	}
	
	@Test
	public void afterPropertiesSetWhenMustacheFactorySetPreservesProperty() throws Exception
	{
		MustacheFactory factory = mock(MustacheFactory.class);
		configurer.setMustacheFactory(factory);

		configurer.afterPropertiesSet();
		
		assertThat(configurer.getMustacheFactory(), is(factory));
	}
	
	@Test
	public void setMustacheFactorySetsProperty()
	{
		MustacheFactory factory = mock(MustacheFactory.class);
		
		configurer.setMustacheFactory(factory);
		
		assertThat(configurer.getMustacheFactory(), is(factory));
	}
}
