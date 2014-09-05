package com.doctusoft.dsw.client.gwt;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Label;
import com.xedge.jquery.client.JQuery;

public class TestLabelRenderer extends AbstractDswebTest {
	
	@Test
	public void testLabelInitialValue() {
		Label label = new Label("hello world").withId("label");
		registerApp(label);
		assertEquals("hello world", JQuery.select("#label").text());
	}
	
	@Test
	public void testLabelUpdate() {
		Label label = new Label().withId("label");
		registerApp(label);
		label.getModel().setLabel("hello world");
		assertEquals("hello world", JQuery.select("#label").text());
	}
  
	@Test
	public void testLabelElementName() {
		Label label = new Label("hello world", "h1").withId("label");
		registerApp(label);
		assertEquals("hello world", JQuery.select("h1#label").text());
	}
}
