
package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.xedge.jquery.client.JQuery;

public class TestInputTextRenderer extends AbstractDswebTest {
	
	@Test
	public void testValue() {
		InputText inputText = new InputText().withId( "input" );
		InputTextModel model = inputText.getModel();
		model.setValue( "elso" );
		registerApp( inputText );
		assertEquals( "elso", JQuery.select( "#input" ).val() );
		model.setValue( "masodik" );
		assertEquals( "masodik", JQuery.select( "#input" ).val() );
	}
	
	@Test
	public void testInputType() {
		InputText inputText = new InputText().withId( "input" );
		inputText.getModel().setInputType( "password" );
		registerApp( inputText );
		assertEquals( "password", JQuery.select( "#input" ).attr( "type" ) );
	}
	
	@Test
	public void testTyping() {
		final InputText inputText = new InputText().withId( "input" );
		registerApp( inputText );
		JQuery jqInput = JQuery.select( "#input" );
		jqInput.val( "proba" );
		jqInput.change();
		assertEquals( "proba", inputText.getModel().getValue() );
	}
}
