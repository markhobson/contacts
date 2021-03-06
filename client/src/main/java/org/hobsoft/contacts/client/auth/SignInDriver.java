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
package org.hobsoft.contacts.client.auth;

import org.hobsoft.contacts.client.AbstractPageDriver;
import org.hobsoft.contacts.client.DriverConfiguration;
import org.hobsoft.contacts.client.contact.ContactsViewDriver;
import org.hobsoft.microbrowser.MicrodataDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hobsoft.contacts.client.MicrodataSchema.ERROR;
import static org.hobsoft.contacts.client.MicrodataSchema.SUCCESS;

/**
 * Driver for the sign-in page.
 */
@Component
public class SignInDriver extends AbstractPageDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public SignInDriver(DriverConfiguration config, MicrodataDocument document)
	{
		super(config, document, "/login");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public String getSuccessMessage()
	{
		checkVisible();
		
		return document()
			.getItem(SUCCESS)
			.getProperty("message")
			.getValue();
	}

	public String getErrorMessage()
	{
		checkVisible();
		
		return document()
			.getItem(ERROR)
			.getProperty("message")
			.getValue();
	}
	
	public ContactsViewDriver signIn(Credentials credentials)
	{
		MicrodataDocument document = signInImpl(credentials);
		
		return new ContactsViewDriver(getConfiguration(), document);
	}

	public SignInDriver signInWithError(Credentials credentials)
	{
		MicrodataDocument document = signInImpl(credentials);
		
		return new SignInDriver(getConfiguration(), document);
	}

	private MicrodataDocument signInImpl(Credentials credentials)
	{
		checkVisible();

		return document()
			.getForm("login")
			.setParameter("username", credentials.getUsername())
			.setParameter("password", credentials.getPassword())
			.submit();
	}
}
