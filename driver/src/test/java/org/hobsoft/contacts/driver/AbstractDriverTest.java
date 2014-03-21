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
package org.hobsoft.contacts.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.hobsoft.contacts.driver.event.ContactListener;
import org.hobsoft.microbrowser.Link;
import org.hobsoft.microbrowser.Microbrowser;
import org.hobsoft.microbrowser.MicrodataDocument;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
		DriverConfiguration config = createConfig();
		MicrodataDocument document = mock(MicrodataDocument.class);
		
		AbstractDriver driver = new FakeDriver(config, document, "x");
		
		assertEquals("configuration", config, driver.getConfiguration());
		assertEquals("document", document, driver.document());
		assertEquals("selfPathPattern", "x", driver.getSelfPathPattern());
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullDriverConfigurationThrowsException()
	{
		new FakeDriver(null, mock(MicrodataDocument.class), "x");
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullDocumentThrowsException() throws MalformedURLException
	{
		new FakeDriver(createConfig(), null, "x");
	}
	
	@Test(expected = NullPointerException.class)
	public void constructorWithNullSelfPathPatternThrowsException() throws MalformedURLException
	{
		new FakeDriver(createConfig(), mock(MicrodataDocument.class), null);
	}
	
	@Test
	public void isVisibleWhenSelfEqualReturnsTrue() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(new URL("http://localhost/x"));
		AbstractDriver driver = new FakeDriver(createConfig(), document, "/x");
		
		assertTrue(driver.isVisible());
	}

	@Test
	public void isVisibleWhenSelfUnequalReturnsFalse() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(new URL("http://localhost/y"));
		AbstractDriver driver = new FakeDriver(createConfig(), document, "/x");
		
		assertFalse(driver.isVisible());
	}
	
	@Test
	public void isVisibleWhenSelfMissingReturnsFalse() throws MalformedURLException
	{
		MicrodataDocument document = mock(MicrodataDocument.class);
		when(document.hasLink("self")).thenReturn(false);
		AbstractDriver driver = new FakeDriver(createConfig(), document, "x");
		
		assertFalse(driver.isVisible());
	}
	
	@Test
	public void isVisibleWhenSelfNullReturnsFalse() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(null);
		AbstractDriver driver = new FakeDriver(createConfig(), document, "x");
		
		assertFalse(driver.isVisible());
	}
	
	@Test
	public void checkVisibleWhenSelfEqualReturns() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(new URL("http://localhost/x"));
		AbstractDriver driver = new FakeDriver(createConfig(), document, "/x");
		
		driver.checkVisible();
	}
	
	@Test
	public void checkVisibleWhenSelfUnequalThrowsException() throws MalformedURLException
	{
		MicrodataDocument document = newDocumentWithSelf(new URL("http://localhost/y"));
		AbstractDriver driver = new FakeDriver(createConfig(), document, "/x");
		
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

	private static DriverConfiguration createConfig() throws MalformedURLException
	{
		return new DriverConfiguration(mock(Microbrowser.class), createUrl(), mock(ContactListener.class));
	}

	private static URL createUrl() throws MalformedURLException
	{
		return new URL("http://localhost/");
	}
	
	private static MicrodataDocument newDocumentWithSelf(URL selfHref)
	{
		Link self = mock(Link.class);
		when(self.getHref()).thenReturn(selfHref);
		
		MicrodataDocument document = mock(MicrodataDocument.class);
		when(document.hasLink("self")).thenReturn(true);
		when(document.getLink("self")).thenReturn(self);
		
		return document;
	}
}
