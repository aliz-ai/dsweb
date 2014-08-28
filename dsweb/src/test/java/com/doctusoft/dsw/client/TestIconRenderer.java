
package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Icon;
import com.doctusoft.dsw.client.gwt.BootstrapIcon;
import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;
import com.xedge.jquery.client.JQuery;

public class TestIconRenderer extends AbstractDswebTest {
	
	@Test
	public void testIcon() {
		Icon icon = new Icon( BootstrapIcon.ICON_ARROW_LEFT ).withId( "icon" );
		registerApp( icon );
		JQuery jqIcon = JQuery.select( "#icon" );
		assertTrue( jqIcon.hasClass( BootstrapIcon.ICON_ARROW_LEFT.getClassName() ) );
		icon.getModel().setIcon( BootstrapIcon.ICON_ADJUST );
		assertFalse( jqIcon.hasClass( BootstrapIcon.ICON_ARROW_LEFT.getClassName() ) );
		assertTrue( jqIcon.hasClass( BootstrapIcon.ICON_ADJUST.getClassName() ) );
	}
	
	@Test
	public void testWhite() {
		Icon icon = new Icon( BootstrapIcon.ICON_ARROW_LEFT ).withId( "icon" );
		icon.getModel().setWhite( true );
		registerApp( icon );
		JQuery jqIcon = JQuery.select( "#icon" );
		assertTrue( jqIcon.hasClass( BootstrapStyleClasses.ICON_WHITE ) );
		icon.getModel().setWhite( false );
		assertFalse( jqIcon.hasClass( BootstrapStyleClasses.ICON_WHITE ) );
	}
	
}
