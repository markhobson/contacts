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
package org.hobsoft.contacts.server.support.guice;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import com.google.inject.Guice;
import com.google.inject.Module;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Adds Guice module support to Jersey's {@code ResourceConfig}.
 * <p>
 * Use {@link #module(Module)} to add Guice modules to Jersey's HK2 injection.
 * 
 * @see <a href="https://hk2.java.net/guice-bridge/index.html">The Guice/HK2 Bridge</a>
 */
public class GuiceResourceConfig extends ResourceConfig
{
	// NOTE: works with the caveat of JERSEY-1950
	
	// fields -----------------------------------------------------------------
	
	private final ServiceLocator serviceLocator;
	
	// constructors -----------------------------------------------------------
	
	@Inject
	public GuiceResourceConfig(ServiceLocator serviceLocator)
	{
		this.serviceLocator = checkNotNull(serviceLocator);
		
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
	}
	
	// public methods ---------------------------------------------------------
	
	/**
	 * Adds the specified Guice module to this application.
	 * 
	 * @param module
	 *            the Guice module to add
	 * @return this application
	 */
	public GuiceResourceConfig module(Module module)
	{
		GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		
		// TODO: support multiple modules
		guiceBridge.bridgeGuiceInjector(Guice.createInjector(module));
		
		return this;
	}
}
