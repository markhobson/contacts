package org.hobsoft.contacts.server.support.mustache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.internal.DefaultTemplateProcessor;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.Charsets;

/**
 * Jersey MVC template processor for Mustache.java.
 */
public class MustacheViewProcessor extends DefaultTemplateProcessor<String>
{
	// constants --------------------------------------------------------------
	
	private static final String EXTENSION = ".mustache";
	
	// fields -----------------------------------------------------------------
	
	private final MustacheFactory mustacheFactory;
	
	@Context
	private UriInfo uriInfo;
	
	// constructors -----------------------------------------------------------
	
	public MustacheViewProcessor(@Context Configuration configuration)
	{
		super(configuration);
		
		mustacheFactory = new DefaultMustacheFactory();
		
		setBasePathFromProperty(MustacheProperties.TEMPLATES_BASE_PATH);
	}
	
	// TemplateProcessor methods ----------------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String resolve(String name, MediaType mediaType)
	{
		Class<?> lastMatchedResourceClass = getLastMatchedResourceClass();

		for (String templateName : getPossibleTemplateNames(name))
		{
			if (lastMatchedResourceClass.getResource(templateName) != null)
			{
				return templateName;
			}
		}

		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeTo(String templateReference, Viewable viewable, MediaType mediaType, OutputStream out)
		throws IOException
	{
		// commit the status and headers to the HttpServletResponse
		out.flush();

		InputStream inputStream = getLastMatchedResourceClass().getResourceAsStream(templateReference);
		Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8));
		
		Mustache mustache = mustacheFactory.compile(reader, templateReference);
		mustache.execute(new OutputStreamWriter(out), viewable.getModel()).flush();
	}
	
	// DefaultTemplateProcessor methods ---------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	protected List<String> getExtensions()
	{
		return Collections.singletonList(EXTENSION);
	}
	
	// private methods --------------------------------------------------------

	private Class<?> getLastMatchedResourceClass()
	{
		return uriInfo.getMatchedResources().get(0).getClass();
	}
}
