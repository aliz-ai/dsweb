package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Label;
import com.xedge.jquery.client.JQuery;

public class TestLabelRenderer extends AbstractDswebTest {
	
	@Test
	public void testLabelInitialValue() {
		
	}
	
	@Test
	public void testLabelUpdate() {
		Label label = new Label().withStyleClass("label");
		registerApp(label);
		label.getModel().setLabel("hello world");
		assertEquals("hello world", JQuery.select(".label").text());
	}
  
}
