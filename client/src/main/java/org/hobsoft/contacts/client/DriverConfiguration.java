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
package org.hobsoft.contacts.client;

import java.net.URL;

import org.hobsoft.contacts.client.contact.ContactListener;
import org.hobsoft.microbrowser.Microbrowser;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Configuration for web application drivers.
 */
public final class DriverConfiguration
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------
	
	private final Microbrowser browser;
	
	private final URL serverUrl;

	private final ContactListener contactListener;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	public DriverConfiguration(Microbrowser browser, URL serverUrl, ContactListener contactListener)
	{
		this.browser = checkNotNull(browser, "browser");
		this.serverUrl = checkNotNull(serverUrl, "serverUrl");
		this.contactListener = checkNotNull(contactListener, "contactListener");
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public Microbrowser getBrowser()
	{
		return browser;
	}
	
	public URL getServerUrl()
	{
		return serverUrl;
	}
	
	public ContactListener getContactListener()
	{
		return contactListener;
	}
}
