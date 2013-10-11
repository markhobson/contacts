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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Web UI driver for the root page.
 */
@Component
public class RootDriver extends AbstractDriver
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------
	
	@Autowired
	public RootDriver(DriverConfiguration config)
	{
		super(config);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// Driver methods
	// ----------------------------------------------------------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVisible()
	{
		return false;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public void show()
	{
		driver().get(url("/"));
	}
}
