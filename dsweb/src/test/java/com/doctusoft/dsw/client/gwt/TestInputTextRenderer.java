
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
		final InputText inputText = new InputText().withId("input").withEnabled(false);
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
		inputText.withEnabled(false);
		assertTrue(JQuery.select("#input").is(":disabled"));
		inputText.withEnabled(true);
		assertFalse(JQuery.select("#input").is(":disabled"));
	}
	
	@Test
	public void testMaxLength() {
		final InputText inputText = new InputText().withId("input").withMaxLength(5);
		registerApp(inputText);
		InputTextModel model = inputText.getModel();
		model.setValue( "123456" );
		assertEquals("12345", inputText.getModel().getValue());
		JQuery jqInput = JQuery.select("#input");
		jqInput.val("456789");
		jqInput.change();
		assertEquals("45678", inputText.getModel().getValue());
	}
	
	@Test
	public void testTypingWithImmediatePropagation() {
		final InputText inputText = new InputText().withId("input").withMaxLength(12).withImmediate(false);
		registerApp(inputText);
		JQuery jqInput = JQuery.select("#input");
		
		// with immediate set to FALSE
		jqInput.val("T");
		jqInput.keyup();
		jqInput.val("Th");
		jqInput.keyup();
		assertEquals(null, inputText.getModel().getValue());
		
		// with immediate set to TRUE
		inputText.immediate();
		jqInput.val("T");
		jqInput.keyup();
		assertEquals("T", inputText.getModel().getValue());
		jqInput.val("Th");
		jqInput.keyup();
		assertEquals("Th", inputText.getModel().getValue());
		jqInput.val("Thi");
		jqInput.keyup();
		assertEquals(3, inputText.getModel().getValue().length());
		jqInput.val("This is cool");
		jqInput.keyup();
		assertEquals(12, inputText.getModel().getValue().length());
		jqInput.val("This is cool!");
		jqInput.keyup();
		assertEquals(12, inputText.getModel().getValue().length());
		assertEquals("This is cool", inputText.getModel().getValue());
		dumpRoot();
	}
	
}
