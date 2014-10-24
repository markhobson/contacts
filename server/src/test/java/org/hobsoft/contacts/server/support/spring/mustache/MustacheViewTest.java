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

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.github.mustachejava.DefaultMustacheFactory;

import static java.util.Collections.singletonMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests {@code MustacheView}.
 */
public class MustacheViewTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// constants
	// ----------------------------------------------------------------------------------------------------------------

	private static final String TEMPLATE_PATH = "org/hobsoft/contacts/server/support/spring/mustache/";

	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private MustacheView view;
	
	private MockHttpServletResponse response;

	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp()
	{
		view = new MustacheView();
		view.setMustacheFactory(new DefaultMustacheFactory());
		
		response = new MockHttpServletResponse();
	}

	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void renderReturnsContent() throws Exception
	{
		view.setUrl(TEMPLATE_PATH + "template.mustache");
		
		view.render(new HashMap<String, Object>(), new MockHttpServletRequest(), response);
		
		assertThat(response.getContentAsString(), is("x"));
	}
	
	@Test
	public void renderWithModelReturnsContent() throws Exception
	{
		view.setUrl(TEMPLATE_PATH + "templateWithModel.mustache");
		Map<String, ?> model = singletonMap("x", "y");
		
		view.render(model, new MockHttpServletRequest(), response);
		
		assertThat(response.getContentAsString(), is("y"));
	}
}
