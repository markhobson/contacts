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

import org.hobsoft.contacts.driver.auth.SignInDriver;
import org.hobsoft.microbrowser.MicrodataDocument;

/**
 * Base web UI driver for common page elements.
 */
public abstract class AbstractPageDriver extends AbstractDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public AbstractPageDriver(DriverConfiguration config, MicrodataDocument document, String self)
	{
		super(config, document, self);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public boolean isSignOutVisible()
	{
		return document().hasLink("logout");
	}
	
	public SignInDriver signOut()
	{
		MicrodataDocument document = document().getLink("logout").follow();
		
		return new SignInDriver(getConfiguration(), document);
	}
}
