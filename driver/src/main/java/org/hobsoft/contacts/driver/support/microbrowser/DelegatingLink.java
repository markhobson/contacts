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
import org.hobsoft.microbrowser.MicrodataDocument;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@code Link} that delegates to another instance.
 */
public abstract class DelegatingLink implements Link
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final Link delegate;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public DelegatingLink(Link delegate)
	{
		this.delegate = checkNotNull(delegate, "delegate");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// Link methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRel()
	{
		return delegate.getRel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getHref()
	{
		return delegate.getHref();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MicrodataDocument follow()
	{
		return delegate.follow();
	}
}
