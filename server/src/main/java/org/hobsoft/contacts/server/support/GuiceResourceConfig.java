package org.hobsoft.contacts.server.support;

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
	
	public GuiceResourceConfig module(Module module)
	{
		GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		
		// TODO: support multiple modules
		guiceBridge.bridgeGuiceInjector(Guice.createInjector(module));
		
		return this;
	}
}
