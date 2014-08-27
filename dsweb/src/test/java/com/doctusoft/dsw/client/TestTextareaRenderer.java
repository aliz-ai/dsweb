package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Textarea;
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

}
