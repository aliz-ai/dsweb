package com.doctusoft.dsw.client;

import org.junit.Test;

import com.doctusoft.dsw.client.comp.Tab;
import com.doctusoft.dsw.client.comp.TabSheet;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
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
		BaseComponentModel tabComponentModel = new BaseComponentModel();
		Tab tab = new Tab(tabCaption).withContent(tabComponentModel);
	}
	
}
