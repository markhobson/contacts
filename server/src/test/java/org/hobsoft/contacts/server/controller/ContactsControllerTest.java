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

import org.hobsoft.contacts.server.ContactsConfig;
import org.hobsoft.contacts.server.dao.FakeContactDao;
import org.hobsoft.contacts.server.support.spring.JspxViewResolver;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Tests {@code ContactsController}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ContactsConfig.class)
public class ContactsControllerTest
{
	// fields -----------------------------------------------------------------
	
	private MockMvc mvc;
	
	// public methods ---------------------------------------------------------
	
	@Before
	public void setUp()
	{
		mvc = MockMvcBuilders.standaloneSetup(new ContactsController(new FakeContactDao()))
			.setViewResolvers(new JspxViewResolver())
			.build();
	}
	
	// tests ------------------------------------------------------------------
	
	@Ignore("spring-test cannot render JSPs")
	@Test
	public void getReturnsHeader() throws Exception
	{
		mvc.perform(get("/contacts"))
			.andExpect(content().string(containsString("<h1>Contacts</h1>")));
	}
	
	@Ignore("spring-test cannot render JSPs")
	@Test
	public void getReturnsContacts() throws Exception
	{
		mvc.perform(get("/contacts"))
			.andExpect(content().string(containsString("<li>A</li>")))
			.andExpect(content().string(containsString("<li>B</li>")))
			.andExpect(content().string(containsString("<li>C</li>")));
	}
}
