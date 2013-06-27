package org.hobsoft.contacts.server.support.mustache;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.jersey.server.mvc.MvcFeature;

/**
 * Jersey MVC feature for Mustache.java.
 */
@ConstrainedTo(RuntimeType.SERVER)
public class MustacheMvcFeature implements Feature
{
	// Feature methods --------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean configure(FeatureContext context)
	{
		if (!context.getConfiguration().isRegistered(MvcFeature.class))
		{
			context.register(MvcFeature.class);
		}
		
		context.register(MustacheViewProcessor.class);
		
		return true;
	}
}
