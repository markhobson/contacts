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

import org.hobsoft.microbrowser.Form;
import org.hobsoft.microbrowser.MicrodataDocument;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@code Form} that delegates to another instance.
 */
public abstract class DelegatingForm implements Form
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private final Form delegate;
	
	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public DelegatingForm(Form delegate)
	{
		this.delegate = checkNotNull(delegate, "delegate");
	}

	// ----------------------------------------------------------------------------------------------------------------
	// Form methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return delegate.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getParameter(String name)
	{
		return delegate.getParameter(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Form setParameter(String name, String value)
	{
		delegate.setParameter(name, value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MicrodataDocument submit()
	{
		return delegate.submit();
	}
}
