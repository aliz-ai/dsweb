
package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.Link;
import com.xedge.jquery.client.JQuery;

public class TestLinkRenderer extends AbstractDswebTest {
	
	@ObservableProperty
	private String text;
	
	@ObservableProperty
	private String href;
	
	@Test
	public void testLinkIsPresent() {
		registerApp( new Link().withId( "link" ) );
		assertEquals( 1, JQuery.select( "#link" ).length() );
	}
	
	@Test
	public void testText() {
		String initialText = "proba";
		setText( initialText );
		registerApp( new Link().withId( "link" ).bindText( Bindings.obs( this ).get( TestLinkRenderer_._text ) ) );
		assertEquals( initialText, JQuery.select( "#link" ).text() );
		String changedText = "uj";
		setText( changedText );
		assertEquals( changedText, JQuery.select( "#link" ).text() );
	}
	
	@Test
	public void testHref() {
		String initialHref = "regi.html";
		setHref( initialHref );
		registerApp( new Link().withId( "link" ).bindHref( Bindings.obs( this ).get( TestLinkRenderer_._href ) ) );
		assertEquals( initialHref, JQuery.select( "#link" ).prop( "href" ).replaceFirst( "^.*/", "" ) ); //replacement is necessary, because the rendered value is an absolute path
		String changedHref = "uj.html";
		setHref( changedHref );
		assertEquals( changedHref, JQuery.select( "#link" ).prop( "href" ).replaceFirst( "^.*/", "" ) );
	}
	
	@Test
	public void testTarget() {
		String target = "_self";
		registerApp( new Link().withId( "link" ).withTarget( target ) );
		assertEquals( target, JQuery.select( "#link" ).prop( "target" ) );
	}
	
	@Test
	public void testSimultaneousHrefAndClickListener() {
		String href = "proba";
		registerApp( new Link().withId( "link" ).withHref( href ).click( new EmptyEventHandler() {
			
			@Override
			public void handle() {
				//actual ClickHandler is not important for the test case
			}
		} ) );
		assertEquals( "javascript:;", JQuery.select( "#link" ).prop( "href" ) );
	}
	
}
