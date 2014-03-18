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

import org.hobsoft.microbrowser.Link;
import org.hobsoft.microbrowser.MicrodataItem;
import org.hobsoft.microbrowser.MicrodataProperty;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@code MicrodataItem} that delegates to another instance.
 */
public class DelegatingMicrodataItem implements MicrodataItem
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final MicrodataItem delegate;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public DelegatingMicrodataItem(MicrodataItem delegate)
	{
		this.delegate = checkNotNull(delegate, "delegate");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// MicrodataItem methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType()
	{
		return delegate.getType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MicrodataProperty getProperty(String name)
	{
		return delegate.getProperty(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Link getLink(String rel)
	{
		return delegate.getLink(rel);
	}
}
