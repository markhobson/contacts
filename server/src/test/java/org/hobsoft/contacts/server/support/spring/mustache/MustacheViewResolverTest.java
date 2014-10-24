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
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.servlet.View;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code MustacheViewResolver}.
 */
public class MustacheViewResolverTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private MustacheViewResolver viewResolver;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		viewResolver = new MustacheViewResolver();
		viewResolver.setApplicationContext(new GenericApplicationContext());
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void resolveViewNameWithTemplateReturnsView() throws Exception
	{
		View actual = viewResolver.resolveViewName("x", null);
		
		assertThat(actual, is(instanceOf(MustacheView.class)));
		assertThat("url", ((MustacheView) actual).getUrl(), is("x"));
	}
}
