package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.Tab;
import com.doctusoft.dsw.client.comp.TabSheet;
import com.xedge.jquery.client.JQuery;

public class TestTabSheetRenderer extends AbstractDswebTest {

	@Test
	public void testTabSheetIsPresent() {
		TabSheet tabSheet = new TabSheet().withId("tabSheet");
		registerApp(tabSheet);
		assertEquals(1, JQuery.select("#tabSheet").length());
	}
	
	@Test
	public void testTabadding() {
		final String tabCaption = "Tab1";
		Container tabComponent = new Container().withId("tabContent");
		Tab tab = new Tab(tabCaption).withContent(tabComponent.getModel());
		TabSheet tabSheet = new TabSheet().withTab(tab);
		registerApp(tabSheet);
		assertEquals(1, JQuery.select("#tabContent").length());
	}
	
}
