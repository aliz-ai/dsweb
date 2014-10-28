
package com.doctusoft.dsw.client.gwt;

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

	@Test
	public void testPlaceHolder() {
		final InputText inputText = new InputText().withId("input").withPlaceHolder("ph");
		registerApp(inputText);
		JQuery jqInput = JQuery.select("#input");
		String actualPlaceholder = jqInput.attr("placeholder");
		assertEquals("ph", actualPlaceholder);
		inputText.withPlaceHolder("changed");
		actualPlaceholder = jqInput.attr("placeholder");
		assertEquals("changed", actualPlaceholder);
	}
	
	@Test
	public void testDisabledFirst() {
		final InputText inputText = new InputText().withId("input").withDisabled(true);
		registerApp(inputText);
		assertTrue(JQuery.select("#input").is(":disabled"));
	}

	@Test
	public void testDisableDefault() {
		final InputText inputText = new InputText().withId("input");
		registerApp(inputText);
		assertFalse(JQuery.select("#input").is(":disabled"));
	}

	@Test
	public void testDisableLater() {
		final InputText inputText = new InputText().withId("input");
		registerApp(inputText);
		assertFalse(JQuery.select("#input").is(":disabled"));
		inputText.withDisabled(true);
		assertTrue(JQuery.select("#input").is(":disabled"));
		inputText.withDisabled(false);
		assertFalse(JQuery.select("#input").is(":disabled"));
	}
}
