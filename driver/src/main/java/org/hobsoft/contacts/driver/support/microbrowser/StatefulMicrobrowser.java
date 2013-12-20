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
import org.hobsoft.microbrowser.Link;
import org.hobsoft.microbrowser.Microbrowser;
import org.hobsoft.microbrowser.MicrodataDocument;

import static com.google.common.base.Preconditions.checkState;

/**
 * {@code Microbrowser} that maintains the current document as state.
 */
public class StatefulMicrobrowser extends DelegatingMicrobrowser
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private MicrodataDocument document;

	// ----------------------------------------------------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------------------------------------------------

	public StatefulMicrobrowser(Microbrowser delegate)
	{
		super(delegate);
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// Microbrowser methods
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MicrodataDocument get(String url)
	{
		document = newStatefulDocument(super.get(url));
		
		return document;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------
	
	public boolean hasDocument()
	{
		return document != null;
	}

	public MicrodataDocument getDocument()
	{
		checkState(hasDocument(), "No document loaded");
		
		return document;
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------
	
	private MicrodataDocument newStatefulDocument(MicrodataDocument delegate)
	{
		return new DelegatingMicrodataDocument(delegate)
		{
			@Override
			public MicrodataDocument get(String url)
			{
				document = newStatefulDocument(super.get(url));
				
				return document;
			}
			
			@Override
			public Link getLink(String rel)
			{
				return newStatefulLink(super.getLink(rel));
			}
			
			@Override
			public Form getForm(String name)
			{
				return newStatefulForm(super.getForm(name));
			}
		};
	}
	
	private Link newStatefulLink(Link delegate)
	{
		return new DelegatingLink(delegate)
		{
			@Override
			public MicrodataDocument follow()
			{
				document = newStatefulDocument(super.follow());
				
				return document;
			}
		};
	}
	
	private Form newStatefulForm(Form delegate)
	{
		return new DelegatingForm(delegate)
		{
			@Override
			public MicrodataDocument submit()
			{
				document = newStatefulDocument(super.submit());
				
				return document;
			}
		};
	}
}
