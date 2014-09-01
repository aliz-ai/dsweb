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
	public void testDefaultTabadding() {
		final String tabCaption = "Tab1";
		Container tabComponent = new Container().withId("tabContent");
		TabSheet tabSheet = new TabSheet().withDefaultTab(tabCaption, tabComponent.getModel());
		registerApp(tabSheet);
		assertEquals(1, JQuery.select("#tabContent").length());
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
	
	@Test
	public void testDefaultTabaddingOnSpecifiedIndex() {
		final String tabCaption = "Tab1";
		Container tabComponent = new Container().withId("tabContent");
		Tab tab = new Tab(tabCaption).withContent(tabComponent.getModel());
		TabSheet tabSheet = new TabSheet().withTab(tab);
		registerApp(tabSheet);
		assertEquals(1, JQuery.select("#tabContent").length());
	}
	
	@Test
	public void testTabaddingOnSpecifiedIndex() {
		final String tabCaption1 = "Tab1";
		final String tabCaption2 = "Tab2";
		final String tabCaption3 = "Tab3";
		final String tabCaption4 = "Tab4";
		Container tabComponent = new Container();
		Tab tab = new Tab(tabCaption1).withContent(tabComponent.getModel());
		TabSheet tabSheet = new TabSheet()
			.withTab(new Tab(tabCaption1).withContent(tabComponent.getModel()))
			.withTab(new Tab(tabCaption2).withContent(tabComponent.getModel()))
			.withTab(new Tab(tabCaption3).withContent(tabComponent.getModel()))
			.withTabOnSpecifiedIndex(new Tab(tabCaption4).withContent(tabComponent.getModel()), 1);
		registerApp(tabSheet);
		assertEquals(tabCaption4, JQuery.select("li:nth-child(2)").text());
	}
	
}
