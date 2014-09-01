
package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.InputTime;
import com.doctusoft.dsw.client.comp.model.InputTimeModel;
import com.xedge.jquery.client.JQuery;

public class TestInputTimeRenderer extends AbstractDswebTest {
	
	@Test
	public void testSetValueOnModel() {
		InputTime inputTime = new InputTime();
		InputTimeModel model = inputTime.getModel();
		model.setValue( "09:09" );
		registerApp( inputTime );
		JQuery jqInput = JQuery.select( "input" );
		assertEquals( "09:09", jqInput.val() );
	}
	
	@Test
	public void testChangeValueOnWidget() {
		InputTime inputTime = new InputTime();
		registerApp( inputTime );
		JQuery jqInput = JQuery.select( "input" );
		changeInputValue( jqInput, "09:09" );
		InputTimeModel model = inputTime.getModel();
		assertEquals( "09:09", model.getValue() );
		changeInputValue( jqInput, "8:14" );
		assertEquals( "08:14", model.getValue() );
		changeInputValue( jqInput, "1214" );
		assertEquals( "12:14", model.getValue() );
	}
	
	private void changeInputValue( JQuery jqInput, String newVal ) {
		jqInput.val( newVal );
		jqInput.change();
	}
}
