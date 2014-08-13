package com.doctusoft.dsw.sample.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Composite;
import com.doctusoft.dsw.client.comp.Label;
import com.xedge.jquery.client.JQuery;

public class TestLabel extends AbstractDswebTest {
	
	public static class LabelTestApp extends Composite<BaseContainer> {
		public LabelTestApp() {
			super(new BaseContainer());
			new Label()
				.withStyleClass("test").withLabel("hello world")
				.appendTo(root);
		}
	}
	
	@Test
	public void test() {
		registerApp(new LabelTestApp());
		assertEquals("hello world", JQuery.select(".test").text());
	}
  
}
