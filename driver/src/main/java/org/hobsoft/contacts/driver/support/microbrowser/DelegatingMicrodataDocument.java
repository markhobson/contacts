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
package org.hobsoft.contacts.driver.support.microbrowser;

import java.util.List;

import org.hobsoft.microbrowser.Form;
import org.hobsoft.microbrowser.Link;
import org.hobsoft.microbrowser.MicrodataDocument;
import org.hobsoft.microbrowser.MicrodataItem;

/**
 * {@code MicrodataDocument} that delegates to another instance.
 */
public abstract class DelegatingMicrodataDocument extends DelegatingMicrobrowser implements MicrodataDocument
{
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public DelegatingMicrodataDocument(MicrodataDocument delegate)
	{
		super(delegate);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// MicrodataDocument methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MicrodataItem getItem(String type)
	{
		return getDelegate().getItem(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MicrodataItem> getItems(String type)
	{
		return getDelegate().getItems(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasLink(String rel)
	{
		return getDelegate().hasLink(rel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Link getLink(String rel)
	{
		return getDelegate().getLink(rel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Form getForm(String name)
	{
		return getDelegate().getForm(name);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCookie(String name)
	{
		return getDelegate().getCookie(name);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// DelegatingMicrobrowser methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MicrodataDocument getDelegate()
	{
		return (MicrodataDocument) super.getDelegate();
	}
}
