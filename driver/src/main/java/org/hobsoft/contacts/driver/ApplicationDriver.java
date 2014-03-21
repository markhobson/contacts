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
package org.hobsoft.contacts.driver;

import org.hobsoft.contacts.driver.auth.Credentials;
import org.hobsoft.contacts.driver.auth.SignInDriver;
import org.hobsoft.contacts.driver.contact.ContactsViewDriver;
import org.hobsoft.microbrowser.MicrodataDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Entry point for the web application driver.
 */
@Component
public class ApplicationDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final DriverConfiguration config;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public ApplicationDriver(DriverConfiguration config)
	{
		this.config = checkNotNull(config, "config");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public SignInDriver signInForm()
	{
		String url = config.getServerUrl().toString();
		MicrodataDocument document = config.getBrowser().get(url);
		
		return new SignInDriver(config, document);
	}
	
	public ContactsViewDriver signIn(Credentials credentials)
	{
		return signInForm()
			.signIn(credentials);
	}
}
