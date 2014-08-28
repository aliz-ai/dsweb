
package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Image;
import com.xedge.jquery.client.JQuery;

public class TestImageRenderer extends AbstractDswebTest {
	
	@Test
	public void testSrc() {
		Image image = new Image().withSrc( "roka.jpg" ).withId( "image" );
		registerApp( image );
		JQuery jqImage = JQuery.select( "#image" );
		assertEquals( "roka.jpg", jqImage.attr( "src" ) );
		image.getModel().setSrc( "uj.jpg" );
		assertEquals( "uj.jpg", jqImage.attr( "src" ) );
	}
	
	@Test
	public void testAlt() {
		Image image = new Image().withAlt( "roka" ).withId( "image" );
		registerApp( image );
		JQuery jqImage = JQuery.select( "#image" );
		assertEquals( "roka", jqImage.attr( "alt" ) );
		image.getModel().setAlt( "uj" );
		assertEquals( "uj", jqImage.attr( "alt" ) );
	}
}
