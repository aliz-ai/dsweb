package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Textarea;
import com.doctusoft.dsw.client.comp.model.TextareaModel;
import com.xedge.jquery.client.JQuery;

public class TestTextareaRenderer extends AbstractDswebTest {
	
	@Test
	public void testTextareaIsPresent() {
		Textarea textarea = new Textarea().withId("textArea");
		registerApp(textarea);
		assertEquals(1, JQuery.select("#textArea").length());
	}
	
	@Test
	public void testSetRows() {
		int rows = 4;
		Textarea textarea = new Textarea().setRows(rows).withId("textArea");
		registerApp(textarea);
		assertEquals(rows, Integer.parseInt(JQuery.select("#textArea").attr("rows")));
	}
	
	@Test
	public void testTextChange() {
		String text = "text";
		String text2 = "text2";
		Textarea textArea = new Textarea().withId("textArea");
		textArea.getModel().setValue(text);
		registerApp(textArea);
		assertEquals(text, JQuery.select("#textArea").val());
		textArea.getModel().setValue(text2);
		assertEquals(text2, JQuery.select("#textArea").val());
		
	}

	@Test
	public void testPlaceHolder() {
		final Textarea textArea = new Textarea().withId("textarea").withPlaceHolder("ph");
		registerApp(textArea);
		JQuery jqInput = JQuery.select("#textarea");
		String actualPlaceholder = jqInput.attr("placeholder");
		assertEquals("ph", actualPlaceholder);
		textArea.withPlaceHolder("changed");
		actualPlaceholder = jqInput.attr("placeholder");
		assertEquals("changed", actualPlaceholder);
	}

	@Test
	public void testMaxLength() {
		final Textarea textArea = new Textarea().withId("textarea").withMaxLength(18);
		registerApp(textArea);
		TextareaModel model = textArea.getModel();
		model.setValue( "1234567891234567891" );
		assertEquals("123456789123456789", textArea.getModel().getValue());
		JQuery jqInput = JQuery.select("#textarea");
		jqInput.val("1234567891234567891");
		jqInput.change();
		assertEquals("123456789123456789", textArea.getModel().getValue());
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
		assertEquals("", inputText.getModel().getValue());
		
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
		jqInput.val("This");
		jqInput.keyup();
		jqInput.val("This ");
		jqInput.keyup();
		jqInput.val("This i");
		jqInput.keyup();
		jqInput.val("This is");
		jqInput.keyup();
		jqInput.val("This is ");
		jqInput.keyup();
		jqInput.val("This is c");
		jqInput.keyup();
		jqInput.val("This is co");
		jqInput.keyup();
		jqInput.val("This is coo");
		jqInput.keyup();
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
