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
package org.hobsoft.contacts.test.acceptance;

import org.hobsoft.contacts.client.ApplicationDriver;
import org.hobsoft.contacts.test.acceptance.config.API;
import org.hobsoft.contacts.test.acceptance.config.UI;
import org.hobsoft.contacts.test.acceptance.rule.ContactRule;
import org.hobsoft.contacts.test.acceptance.rule.WebDriverCookieRule;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base acceptance test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AcceptanceTestConfig.class)
public abstract class AbstractIT
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private ContactRule contactRule;
	
	@Autowired
	private WebDriverCookieRule cookieRule;
	
	@Autowired
	@API
	private ApplicationDriver api;
	
	@Autowired
	@UI
	private ApplicationDriver ui;
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	@Rule
	public RuleChain getRuleChain()
	{
		return RuleChain.outerRule(contactRule)
			.around(cookieRule);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// protected methods
	// ----------------------------------------------------------------------------------------------------------------

	protected final ApplicationDriver api()
	{
		return api;
	}
	
	protected final ApplicationDriver ui()
	{
		return ui;
	}
}
