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
package org.hobsoft.contacts.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import org.hobsoft.contacts.client.test.FakeDriver;
import org.hobsoft.microbrowser.Link;
import org.hobsoft.microbrowser.MicrodataDocument;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hobsoft.contacts.client.test.MockDriverConfigurations.newConfig;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests {@code AbstractDriver}.
 */
public class AbstractDriverTest
{
	// ----------------------------------------------------------------------------------------------------------------
	// fields
	// ----------------------------------------------------------------------------------------------------------------

	private ExpectedException thrown = ExpectedException.none();
	
	// ----------------------------------------------------------------------------------------------------------------
	// tests
	// ----------------------------------------------------------------------------------------------------------------

	@Test
	public void constructorSetsProperties() throws MalformedURLException
	{
		DriverConfiguration config = newConfig();
		MicrodataDocument document = mock(MicrodataDocument.class);
		
		AbstractDriver driver = new FakeDriver(config, document, "x");
		
		assertThat("configuration", driver.getConfiguration(), is(config));
		assertThat("document", driver.document(), is(document));
		assertThat("selfPathPattern", driver.getSelfPathPattern(), is("x"));
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullDriverConfigurationThrowsException()
	{
		new FakeDriver(null, mock(MicrodataDocument.class), "x");
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullDocumentThrowsException() throws MalformedURLException
	{
		new FakeDriver(newConfig(), null, "x");
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullSelfPathPatternThrowsException() throws MalformedURLException
	{
		new FakeDriver(newConfig(), mock(MicrodataDocument.class), null);
	}
	
	@Test
	public void isVisibleWhenSelfEqualReturnsTrue() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(new URL("http://localhost/x"));
		AbstractDriver driver = new FakeDriver(newConfig(), document, "/x");
		
		assertThat(driver.isVisible(), is(true));
	}

	@Test
	public void isVisibleWhenSelfUnequalReturnsFalse() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(new URL("http://localhost/y"));
		AbstractDriver driver = new FakeDriver(newConfig(), document, "/x");
		
		assertThat(driver.isVisible(), is(false));
	}
	
	@Test
	public void isVisibleWhenSelfMissingReturnsFalse() throws MalformedURLException
	{
		MicrodataDocument document = mock(MicrodataDocument.class);
		when(document.getLinks("self")).thenReturn(Collections.<Link>emptyList());
		AbstractDriver driver = new FakeDriver(newConfig(), document, "x");
		
		assertThat(driver.isVisible(), is(false));
	}
	
	@Test
	public void isVisibleWhenSelfNullReturnsFalse() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(null);
		AbstractDriver driver = new FakeDriver(newConfig(), document, "x");
		
		assertThat(driver.isVisible(), is(false));
	}
	
	@Test
	public void checkVisibleWhenSelfEqualReturns() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(new URL("http://localhost/x"));
		AbstractDriver driver = new FakeDriver(newConfig(), document, "/x");
		
		driver.checkVisible();
	}
	
	@Test
	public void checkVisibleWhenSelfUnequalThrowsException() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(new URL("http://localhost/y"));
		AbstractDriver driver = new FakeDriver(newConfig(), document, "/x");
		
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("Self expected: /x but was: /y");
		
		driver.checkVisible();
	}
	
	// ----------------------------------------------------------------------------------------------------------------
	// public methods
	// ----------------------------------------------------------------------------------------------------------------

	@Rule
	public ExpectedException getThrown()
	{
		return thrown;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// private methods
	// ----------------------------------------------------------------------------------------------------------------

	private static MicrodataDocument newDocumentWithSelf(URL selfHref)
	{
		Link self = mock(Link.class);
		when(self.getHref()).thenReturn(selfHref);
		
		MicrodataDocument document = mock(MicrodataDocument.class);
		when(document.getLinks("self")).thenReturn(Collections.singletonList(self));
		
		return document;
	}
}
