
package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Link;
import com.xedge.jquery.client.JQuery;

public class TestLinkRenderer extends AbstractDswebTest {
	
	@Test
	public void testText() {
		String initialText = "test";
		Link link = new Link( initialText ).withId( "link" );
		registerApp( link );
		assertEquals( initialText, JQuery.select( "#link" ).text() );
		String changedText = "new";
		link.getModel().setText( changedText );
		assertEquals( changedText, JQuery.select( "#link" ).text() );
	}
	
	@Test
	public void testHref() {
		String initialHref = "test";
		Link link = new Link().withId( "link" ).withHref( initialHref );
		registerApp( link );
		assertEquals( initialHref, JQuery.select( "#link" ).attr( "href" ) );
		String changedHref = "new";
		link.getModel().setHref( changedHref );
		assertEquals( changedHref, JQuery.select( "#link" ).attr( "href" ) );
	}
	
	@Test
	public void testTarget() {
		String target = "_self";
		registerApp( new Link().withId( "link" ).withTarget( target ) );
		assertEquals( target, JQuery.select( "#link" ).attr( "target" ) );
	}
	
	@Test
	public void testSimultaneousHrefAndClickListener() {
		String href = "proba";
		registerApp( new Link().withId( "link" ).withHref( href ).click( new EmptyEventHandlerMock() ) );
		assertEquals( "javascript:;", JQuery.select( "#link" ).attr( "href" ) );
	}
	
}
