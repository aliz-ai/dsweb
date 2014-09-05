
package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Alert;
import com.doctusoft.dsw.client.comp.Alert.AlertDisplayType;
import com.doctusoft.dsw.client.comp.Alert.AlertType;
import com.xedge.jquery.client.JQuery;

public class TestAlertRenderer extends AbstractDswebTest {
	
	@Test
	public void testTitle() {
		Alert alert = new Alert( "", "title" ).withId( "alert" );
		registerApp( alert );
		JQuery jqTitle = JQuery.select( "#alert > strong" );
		assertEquals( "title", jqTitle.text() );
		alert.getModel().setTitle( "new" );
		assertEquals( "new", jqTitle.text() );
	}
	
	@Test
	public void testDescription() {
		Alert alert = new Alert( "description", "title" ).withId( "alert" );
		registerApp( alert );
		JQuery jqDescription = JQuery.select( "#alert > span" );
		assertEquals( "description", jqDescription.text() );
		alert.getModel().setDescription( "d2" );
		assertEquals( "d2", jqDescription.text() );
	}
	
	@Test
	public void testAlertDisplayType() {
		Alert alert = new Alert( "", "" ).withId( "alert" );
		alert.getModel().setAlertDisplayType( AlertDisplayType.TwoLine );
		registerApp( alert );
		assertEquals( 1, JQuery.select( "#alert > h4" ).length() );
	}
	
	@Test
	public void testAlertType() {
		Alert alert = new Alert( "", "" ).withId( "alert" );
		alert.getModel().setAlertType( AlertType.Danger );
		registerApp( alert );
		assertTrue( JQuery.select( "#alert" ).hasClass( "alert-error" ) );
	}
}
