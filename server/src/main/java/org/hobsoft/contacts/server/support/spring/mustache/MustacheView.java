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
package org.hobsoft.contacts.server.support.spring.mustache;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.servlet.view.AbstractTemplateView;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

/**
 * Spring MVC view for a Mustache template.
 */
public class MustacheView extends AbstractTemplateView
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private MustacheFactory mustacheFactory;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public MustacheView()
	{
		setExposeSpringMacroHelpers(false);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// ApplicationObjectSupport methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initApplicationContext()
	{
		super.initApplicationContext();
		
		if (getMustacheFactory() == null)
		{
			setMustacheFactory(autodetectMustacheFactory());
		}
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// AbstractTemplateView methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request,
		HttpServletResponse response) throws Exception
	{
		Mustache mustache = mustacheFactory.compile(getReader(), getUrl());
		mustache.execute(response.getWriter(), model).flush();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	public MustacheFactory getMustacheFactory()
	{
		return mustacheFactory;
	}
	
	public void setMustacheFactory(MustacheFactory mustacheFactory)
	{
		this.mustacheFactory = mustacheFactory;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// protected methods
	// ----------------------------------------------------------------------------------------------------------------

	protected MustacheFactory autodetectMustacheFactory()
	{
		try
		{
			MustacheConfig mustacheConfig = BeanFactoryUtils.beanOfTypeIncludingAncestors(getApplicationContext(),
				MustacheConfig.class, true, false);
			return mustacheConfig.getMustacheFactory();
		}
		catch (NoSuchBeanDefinitionException exception)
		{
			throw new ApplicationContextException("Must define a single MustacheConfig bean in this web application "
				+ "context (may be inherited): MustacheConfigurer is the usual implementation. This bean may be given "
				+ "any name.", exception);
		}
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private Reader getReader() throws IOException
	{
		URL url = Resources.getResource(getUrl());
		
		return Resources.newReaderSupplier(url, Charsets.UTF_8)
			.getInput();
	}
}
